package pl.mobite.sample.ca.mvp.ui.components.edituser

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.View


interface EditUserView: View {

    fun showLoadIndicator()

    fun setUser(user: User)

    fun showUserForm()

    fun showNewUserForm()

    fun showCreateIndicator()

    fun showUpdateIndicator()

    fun showDeleteIndicator()


    fun onCreateSuccess()

    fun onUpdateSuccess()

    fun onDeleteSuccess()

    fun showCreateError()

    fun showUpdateError()

    fun showDeleteError()

    fun showInvalidUserData()

    fun showAppError()

    fun close()
}