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
import pl.mobite.sample.ca.mvp.utils.extensions.inflate


class UsersListPagingAdapter: PagedListAdapter<UserEntity, UserViewHolder>(
        object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity)
                = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity)
                = oldItem == newItem

        }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        = UserViewHolder(parent.inflate(R.layout.item_user))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User? = getItem(position)?.toUser()
        if (user == null) {
            holder.bind(User(0, "........", 0))
        } else {
            holder.bind(user)
        }
    }
}

class UserViewHolder(itemView: View) : CustomViewHolder<User>(itemView) {

    override fun bind(item: User) = with(itemView) {
        userName.text = item.name
        userAge.text = item.age.toString()
    }
}