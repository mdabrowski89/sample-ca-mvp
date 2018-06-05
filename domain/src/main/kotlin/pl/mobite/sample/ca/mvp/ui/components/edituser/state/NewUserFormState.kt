package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.models.UserFormData


class NewUserFormState: AbstractEditUserPresenterState() {

    override fun onCreateUser(userFormData: UserFormData) {
        with(presenter) {
            with(userFormData) {
                if (name != null && age != null) {
                    setNewState(CreateUserState(User(null, name, age)))
                } else {
                    view.showInvalidUserData()
                }
            }
        }
    }

    override fun createSavableInstance() = NewUserFormState()
}