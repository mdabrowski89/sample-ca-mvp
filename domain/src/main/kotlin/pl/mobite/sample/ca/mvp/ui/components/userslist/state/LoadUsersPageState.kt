package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import io.reactivex.disposables.Disposable
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User
import java.io.Serializable


class LoadUsersPageState(
        val pageToLoad: Int
) : AbstractUsersListPresenterState() {

    private var disposable: Disposable? = null

    override fun onApplied() {
        with(presenter) {
            /** If data are loaded for the first time show full screen loader */
            /** If data are refreshed show different loader */
            if (isInitialLoading()) {
                view.showLoadIndicator()
            } else if (pageToLoad == PageMetadata.FIRST_PAGE_INDEX) {
                view.showRefreshIndicator()
            }

            /** Load page from repository, repository implementation is responsible for scheduling */
            disposable = usersRepository.getUsersPage(pageToLoad)
                    .subscribe(
                    /** onSuccess */
                    { newPage: Page<User> ->
                        setNewState(MergeUsersPageState(newPage))
                    },
                    /** onError */
                    { _: Throwable? ->
                        setNewState(DisplayErrorState())
                    }
            )
        }
    }

    override fun onUserClicked(user: User) {
        presenter.view.showUserDetails(user)
    }

    override fun onRefreshUsers() {
        with(presenter) {
            setNewState(LoadInitialUsersPageState())
        }
    }

    override fun onAddUserClicked() {
        with(presenter) {
            view.showNewUserForm()
        }
    }

    override fun onLeft(finished: Boolean) {
        disposable?.dispose()
    }

    private fun isInitialLoading(): Boolean {
        with(presenter) {
            return pageMetadata.isInitialPage || (pageMetadata.isFirst && users.isEmpty())
        }
    }

    override fun createSavableInstance(): Serializable
            = if (isInitialLoading()) LoadInitialUsersPageState() else PresentUsersState()
}