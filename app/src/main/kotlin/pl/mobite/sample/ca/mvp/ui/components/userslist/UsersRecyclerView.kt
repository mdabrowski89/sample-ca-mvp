package pl.mobite.sample.ca.mvp.ui.components.userslist

import android.content.Context
import android.util.AttributeSet
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged.PagedRecyclerView


class UsersRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
): PagedRecyclerView<User, UsersListAdapter>(context, attrs, defStyleAttr) {

    override fun getEmptyViewLayoutRes() = R.layout.empty_view_users

    override fun createAdapter() = UsersListAdapter()
}