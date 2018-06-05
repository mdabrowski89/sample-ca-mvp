package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.models.UserFormData


class EditUserFormState: AbstractEditUserPresenterState() {

    override fun onUpdateUser(userFormData: UserFormData) {
        with(presenter) {
            user?.let {
                with(userFormData) {
                    if (name != null && age != null) {
                        setNewState(UpdateUserState(User(it.id, name, age)))
                    } else {
                        view.showInvalidUserData()
                    }
                }
            } ?: setNewState(ShowErrorAndCloseState())
        }
    }

    override fun onDeleteUser() {
        with(presenter) {
            setNewState(DeleteUserState())
        }
    }

    override fun createSavableInstance() = EditUserFormState()
}