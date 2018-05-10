package pl.mobite.sample.ca.mvp.ui.components.userlist.state

import pl.mobite.sample.ca.mvp.data.models.User
import java.io.Serializable


class PresentUsersState(
        private val pageToLoadOnStart: Int? = null
): AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            view.showUsers(users, !pageMetadata.isLast)
            pageToLoadOnStart?.let {
                setNewState(LoadUsersPageState(pageToLoadOnStart))
            }
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

    override fun onLoadNextUsersPage() {
        with(presenter) {
            setNewState(LoadUsersPageState(pageMetadata.nextIndex))
        }
    }

    override fun createSavableInstance(): Serializable = PresentUsersState(pageToLoadOnStart)
}