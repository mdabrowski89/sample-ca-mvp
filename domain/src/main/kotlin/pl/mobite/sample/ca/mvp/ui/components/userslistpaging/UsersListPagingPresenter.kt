package pl.mobite.sample.ca.mvp.ui.components.userslistpaging

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.AbstractUsersListPagingPresenterState
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.LoadUsersState


class UsersListPagingPresenter(
        view: UsersListPagingView
) : StatablePresenter<AbstractUsersListPagingPresenterState, UsersListPagingView>(view) {

    override fun createInitialState() = LoadUsersState()

    fun onUserClicked(user: User) {
        state?.onUserClicked(user)
    }

    fun onRefreshUsers() {
        state?.onRefreshUsers()
    }

    fun onAddUserClicked() {
        state?.onAddUserClicked()
    }
}