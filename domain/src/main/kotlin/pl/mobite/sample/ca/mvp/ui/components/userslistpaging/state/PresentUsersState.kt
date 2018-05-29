package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.LoadUsersPageState
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.PresentUsersState
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
            setNewState(LoadUsersState())
        }
    }

    override fun onLoadNextUsersPage() {
        with(presenter) {
            setNewState(LoadUsersPageState(pageMetadata.nextIndex))
        }
    }

    override fun createSavableInstance(): Serializable = PresentUsersState(pageToLoadOnStart)
}