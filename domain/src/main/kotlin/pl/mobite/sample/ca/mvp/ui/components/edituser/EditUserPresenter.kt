package pl.mobite.sample.ca.mvp.ui.components.edituser

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.models.UserFormData
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.components.edituser.state.AbstractEditUserPresenterState
import pl.mobite.sample.ca.mvp.ui.components.edituser.state.InitialState
import pl.mobite.sample.ca.mvp.utils.Storage


class EditUserPresenter(
        view: EditUserView,
        val userId: Long?,
        val usersRepository: UsersRepository
) : StatablePresenter<AbstractEditUserPresenterState, EditUserView>(view) {

    var user: User? = null

    override fun createInitialState() = InitialState()

    override fun saveData(storage: Storage) {
        super.saveData(storage)
        user?.let { storage.store(USER_KEY, user) }
    }

    override fun restoreData(storage: Storage) {
        super.restoreData(storage)
        user = storage.restoreSerializable(USER_KEY) as User
    }

    fun onUpdateUser(userFormData: UserFormData) {
        state?.onUpdateUser(userFormData)
    }

    fun onCreateUser(userFormData: UserFormData) {
        state?.onCreateUser(userFormData)
    }

    fun onDeleteUser() {
        state?.onDeleteUser()
    }

    companion object {

        const val USER_KEY = "user"
    }
}