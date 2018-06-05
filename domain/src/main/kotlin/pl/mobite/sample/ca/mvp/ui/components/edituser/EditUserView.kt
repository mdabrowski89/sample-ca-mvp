package pl.mobite.sample.ca.mvp.ui.components.edituser

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.View


interface EditUserView: View {

    fun showLoadIndicator()

    fun showUserForm(user: User)

    fun showNewUserForm()

    fun showCreateIndicator()

    fun showUpdateIndicator()

    fun showDeleteIndicator()


    fun showCreateSuccess()

    fun showUpdateSuccess()

    fun showDeleteSuccess()

    fun showCreateError()

    fun showUpdateError()

    fun showDeleteError()

    fun showInvalidUserData()

    fun showAppError()

    fun close()
}