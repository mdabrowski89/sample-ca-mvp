package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.PresentUsersState
import java.io.Serializable


class PresentUsersState: AbstractUsersListPagingPresenterState() {

    override fun onUserClicked(user: User) {
        with(presenter) {
            view.showUserDetails(user)
        }
    }

    override fun onRefreshUsers() {
        with(presenter) {
            view.showRefreshIndicator()
            view.refreshUsers()
        }
    }

    override fun onAddUserClicked() {
        with(presenter) {
            view.showNewUserForm()
        }
    }

    override fun createSavableInstance(): Serializable = PresentUsersState()
}