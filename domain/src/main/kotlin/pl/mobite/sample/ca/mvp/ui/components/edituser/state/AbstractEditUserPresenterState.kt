package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.base.State
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserPresenter
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserView


abstract class AbstractEditUserPresenterState: State<EditUserView> {

    lateinit var presenter: EditUserPresenter

    final override fun init(presenter: StatablePresenter<*, EditUserView>) {
        this.presenter = presenter as EditUserPresenter
    }

    open fun onUpdateUser(user: User) {}

    open fun onCreateUser(user: User) {}
}