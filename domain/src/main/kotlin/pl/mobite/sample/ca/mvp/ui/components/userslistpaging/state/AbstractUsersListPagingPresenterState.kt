package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.base.State
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.UsersListPagingPresenter
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.UsersListPagingView

abstract class AbstractUsersListPagingPresenterState : State<UsersListPagingView> {

    lateinit var presenter: UsersListPagingPresenter

    final override fun init(presenter: StatablePresenter<*, UsersListPagingView>) {
        this.presenter = presenter as UsersListPagingPresenter
    }

    open fun onUserClicked(user: User) {}

    open fun onRefreshUsers() {}
}
