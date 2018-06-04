package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import pl.mobite.sample.ca.mvp.data.models.User


class PresentUserFormState(val user: User): AbstractEditUserPresenterState() {


    override fun createSavableInstance() = PresentUserFormState(user)
}