package pl.mobite.sample.ca.mvp.ui.components.userslist

import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_user.view.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomViewHolder
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.custompaged.CustomPagedRecyclerViewAdapter
import pl.mobite.sample.ca.mvp.utils.extensions.inflate


class UsersListAdapter: CustomPagedRecyclerViewAdapter<User>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent.inflate(R.layout.item_user))
}

class UserViewHolder(itemView: View) : CustomViewHolder<User>(itemView) {

    override fun bind(item: User) = with(itemView) {
        userName.text = item.name
        userAge.text = item.age.toString()
    }
}