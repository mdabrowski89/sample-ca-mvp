package pl.mobite.sample.ca.mvp.ui.components.userlistpaging

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.synthetic.main.item_user.view.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.local.room.UserEntity
import pl.mobite.sample.ca.mvp.data.local.room.toUser
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomViewHolder
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged.PagedRecyclerViewAdapter
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged.PagedViewHolder
import pl.mobite.sample.ca.mvp.utils.extensions.inflate


class UsersListPagingAdapter: PagedRecyclerViewAdapter<UserEntity>(
        userEntityDiffCallback
) {
    override fun getViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent.inflate(R.layout.item_user))
}

val userEntityDiffCallback = object : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity)
            = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity)
            = oldItem == newItem
}

class UserViewHolder(itemView: View) : PagedViewHolder<UserEntity>(itemView) {

    override fun bind(item: UserEntity) = with(itemView) {
        userName.text = item.name
        userAge.text = item.age.toString()
    }

    override fun bindPlaceholder() = with(itemView) {
        userName.text = "..........."
        userAge.text = "........"
    }
}
