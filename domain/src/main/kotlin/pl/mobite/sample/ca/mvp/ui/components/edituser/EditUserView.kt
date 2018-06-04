package pl.mobite.sample.ca.mvp.ui.components.edituser

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.View


interface EditUserView: View {

    fun showLoadIndicator()

    fun showUpdateUserForm(user: User)

    fun showNewUserForm(user: User)

    fun showSaveIndicator()

    fun showSaveError()
}