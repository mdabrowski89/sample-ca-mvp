package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import io.reactivex.disposables.Disposable
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.RepositoryErrorType
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.DisplayErrorState
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
                        if (newPage.metadata.isValid) {
                            setNewState(MergeUsersPageState(newPage))
                        } else {
                            setNewState(DisplayErrorState(RepositoryErrorType.SERVER))
                        }
                    },
                    /** onError */
                    { _: Throwable? ->
                        setNewState(DisplayErrorState(RepositoryErrorType.NETWORK))
                    }
            )
        }
    }

    override fun onUserClicked(user: User) {
        presenter.view.showUserDetails(user)
    }

    override fun onRefreshUsers() {
        with(presenter) {
            setNewState(LoadUsersState())
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
            = if (isInitialLoading()) LoadUsersState() else PresentUsersState()
}