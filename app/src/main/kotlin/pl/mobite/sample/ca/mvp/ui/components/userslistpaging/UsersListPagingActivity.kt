package pl.mobite.sample.ca.mvp.ui.components.userslistpaging


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import kotlinx.android.synthetic.main.activity_users_list_with_paging.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.local.room.UserEntity
import pl.mobite.sample.ca.mvp.data.local.room.toUser
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.activity.BasePresenterActivity
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserActivity


class UsersListPagingActivity : BasePresenterActivity<UsersListPagingPresenter>(), UsersListPagingView {

    private val viewModel: UsersListPagingViewModel by lazy {
        ViewModelProviders.of(this).get(UsersListPagingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users_list_with_paging)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = UsersListPagingPresenter(this)

        initUsersView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.users_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(it.itemId) {
                android.R.id.home -> finish()
                R.id.menuItemAddUser -> presenter.onAddUserClicked()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initUsersView() {
        usersView.onSwipedToRefreshListener = {
            presenter.onRefreshUsers()
        }

        usersView.onEmptyViewClickedListener = {
            presenter.onRefreshUsers()
        }

        usersView.onItemClickedListener = {
            presenter.onUserClicked(it.toUser())
        }
    }

    override fun showLoadIndicator() {
        usersView.setLoading()
    }

    override fun loadUsers() {
        viewModel.users.observe(this, Observer<PagedList<UserEntity>>( { userEntityList ->
            userEntityList?.let {
                usersView.setItems(it)
            }
        }))
    }

    override fun showRefreshIndicator() {
        usersView.setRefreshing()
    }

    override fun refreshUsers() {
        viewModel.users.value?.dataSource?.invalidate()
    }

    override fun showUserDetails(user: User) {
        startActivity(EditUserActivity.createIntent(this, user))
    }

    override fun showNewUserForm() {
        startActivity(EditUserActivity.createIntene(this))
    }

    companion object {

        fun createIntent(context: Context) = Intent(context, UsersListPagingActivity::class.java)
    }
}
