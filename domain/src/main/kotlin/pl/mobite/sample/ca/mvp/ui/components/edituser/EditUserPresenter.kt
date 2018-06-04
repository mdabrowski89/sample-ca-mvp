package pl.mobite.sample.ca.mvp.ui.components.edituser

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.components.edituser.state.AbstractEditUserPresenterState
import pl.mobite.sample.ca.mvp.utils.Storage


class EditUserPresenter(
        view: EditUserView,
        val userId: Long?,
        val usersRepository: UsersRepository
) : StatablePresenter<AbstractEditUserPresenterState, EditUserView>(view) {

    var user: User? = null

    override fun createInitialState(): AbstractEditUserPresenterState {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveData(storage: Storage) {
        super.saveData(storage)
        user?.let { storage.store(USER_KEY, user) }
    }

    override fun restoreData(storage: Storage) {
        super.restoreData(storage)
        user = storage.restoreSerializable(USER_KEY) as User
    }

    fun onUpdateUser(user: User) {
        state?.onUpdateUser(user)
    }

    fun onCreateUser(user: User) {
        state?.onCreateUser(user)
    }

    companion object {

        const val USER_KEY = "user"
    }
}