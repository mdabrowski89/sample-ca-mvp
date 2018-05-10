package pl.mobite.sample.ca.mvp.ui.components.userlist.state

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.base.State
import pl.mobite.sample.ca.mvp.ui.components.userlist.UsersListPresenter
import pl.mobite.sample.ca.mvp.ui.components.userlist.UsersListView

abstract class AbstractUsersListPresenterState : State<UsersListView> {

    lateinit var presenter: UsersListPresenter

    final override fun init(presenter: StatablePresenter<*, UsersListView>) {
        this.presenter = presenter as UsersListPresenter
    }

    open fun onUserClicked(user: User) {}

    open fun onRefreshUsers() {}

    open fun onLoadNextUsersPage() {}
}
