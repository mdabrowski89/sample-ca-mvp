package pl.mobite.sample.ca.mvp.ui.components.userlistpaging

import android.content.Context
import android.util.AttributeSet
import pl.mobite.sample.ca.mvp.data.local.room.UserEntity
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged.PagedRecyclerView


class UsersListPagingRecyclerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
): PagedRecyclerView<UserEntity, UsersListPagingAdapter>(context, attrs, defStyleAttr) {

    override fun createAdapter() = UsersListPagingAdapter()
}

