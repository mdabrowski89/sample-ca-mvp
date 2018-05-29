package pl.mobite.sample.ca.mvp.ui.components.userlistpaging


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_users_list_with_paging.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.local.room.UserEntity
import pl.mobite.sample.ca.mvp.ui.base.activity.BaseActivity

class UsersLisPagingActivity : BaseActivity() {

    private val viewModel: UsersListPagingViewModel by lazy {
        ViewModelProviders.of(this).get(UsersListPagingViewModel::class.java)
    }

    private val adapter = UsersListPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users_list_with_paging)

        usersView.layoutManager = LinearLayoutManager(this)
        usersView.adapter = adapter

        viewModel.users.observe(this, Observer<PagedList<UserEntity>>( { userEntityList ->
            userEntityList?.let {
                adapter.submitList(it)
            }
        }))
    }

    companion object {

        fun createIntent(context: Context) = Intent(context, UsersLisPagingActivity::class.java)
    }
}
