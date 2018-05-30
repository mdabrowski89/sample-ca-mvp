package pl.mobite.sample.ca.mvp.ui.components.userslist

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.View


interface UsersListView: View {

    fun showLoadIndicator()

    fun showUsers(users: List<User>, hasMore: Boolean)

    fun showInitialError()

    fun showRefreshIndicator()

    fun showUserDetails(user: User)

    fun showError()

}

