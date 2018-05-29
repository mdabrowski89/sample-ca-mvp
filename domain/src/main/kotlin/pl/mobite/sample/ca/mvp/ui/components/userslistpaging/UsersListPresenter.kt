package pl.mobite.sample.ca.mvp.ui.components.userslistpaging

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.AbstractUsersListPresenterState
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.LoadUsersState


class UsersListPresenter(
        view: UsersListView,
        val usersRepository: UsersRepository
) : StatablePresenter<AbstractUsersListPresenterState, UsersListView>(view) {

    override fun createInitialState() = LoadUsersState()

    fun onUserClicked(user: User) {
        state?.onUserClicked(user)
    }

    fun onRefreshUsers() {
        state?.onRefreshUsers()
    }
}