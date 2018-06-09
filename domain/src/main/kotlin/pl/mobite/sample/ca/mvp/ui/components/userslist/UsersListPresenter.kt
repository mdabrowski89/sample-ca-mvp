package pl.mobite.sample.ca.mvp.ui.components.userslist

import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.PageMetadata.Companion.INITIAL_PAGE_INDEX
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.StatablePresenter
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.AbstractUsersListPresenterState
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.LoadInitialUsersPageState
import pl.mobite.sample.ca.mvp.utils.Storage
import java.io.Serializable


class UsersListPresenter(
        view: UsersListView,
        val usersRepository: UsersRepository
) : StatablePresenter<AbstractUsersListPresenterState, UsersListView>(view) {

    var users = listOf<User>()
    var pageMetadata = PageMetadata(INITIAL_PAGE_INDEX, INITIAL_PAGE_INDEX)

    override fun createInitialState() = LoadInitialUsersPageState()

    override fun saveData(storage: Storage) {
        storage.store(USERS_LIST_KEY, users as? Serializable)
        storage.store(PAGE_METADATA_KEY, pageMetadata as? Serializable)
    }

    @Suppress("UNCHECKED_CAST")
    override fun restoreData(storage: Storage) {
        users = storage.restoreSerializable(USERS_LIST_KEY) as? List<User> ?: users
        pageMetadata = storage.restoreSerializable(PAGE_METADATA_KEY) as? PageMetadata ?: pageMetadata
    }

    fun onUserClicked(user: User) {
        state?.onUserClicked(user)
    }

    fun onRefreshUsers() {
        state?.onRefreshUsers()
    }

    fun onLoadNextUsersPage() {
        state?.onLoadNextUsersPage()
    }

    fun onAddUserClicked() {
        state?.onAddUserClicked()
    }

    companion object {

        const val USERS_LIST_KEY = "usersList"
        const val PAGE_METADATA_KEY = "pageMetadata"
    }
}