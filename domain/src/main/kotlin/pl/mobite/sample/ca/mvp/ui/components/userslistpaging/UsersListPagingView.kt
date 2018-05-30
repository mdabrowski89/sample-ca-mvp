package pl.mobite.sample.ca.mvp.ui.components.userslistpaging

import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.View


interface UsersListPagingView: View {

    fun showLoadIndicator()

    fun loadUsers()

    fun showRefreshIndicator()

    fun refreshUsers()

    fun showUserDetails(user: User)

}

