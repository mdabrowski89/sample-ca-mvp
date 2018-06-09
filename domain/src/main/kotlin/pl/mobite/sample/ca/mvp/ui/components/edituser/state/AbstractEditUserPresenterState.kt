package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import pl.mobite.sample.ca.mvp.data.models.UserFormData
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.base.State
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserPresenter
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserView


abstract class AbstractEditUserPresenterState: State<EditUserView> {

    lateinit var presenter: EditUserPresenter

    final override fun init(presenter: StatablePresenter<*, EditUserView>) {
        this.presenter = presenter as EditUserPresenter
    }

    open fun onUpdateUser(userFormData: UserFormData) {}

    open fun onCreateUser(userFormData: UserFormData) {}

    open fun onDeleteUser() {}
}