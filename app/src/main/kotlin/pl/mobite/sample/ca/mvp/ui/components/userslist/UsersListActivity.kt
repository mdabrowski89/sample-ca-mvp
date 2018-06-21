package pl.mobite.sample.ca.mvp.ui.components.userslist


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_users_list.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.activity.BasePresenterActivity
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserActivity
import pl.mobite.sample.ca.mvp.utils.extensions.appComponent
import pl.mobite.sample.ca.mvp.utils.extensions.visible
import javax.inject.Inject

class UsersListActivity : BasePresenterActivity<UsersListPresenter>(), UsersListView {

    @Inject lateinit var usersRepository: UsersRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        appComponent.inject(this)

        presenter = UsersListPresenter(this, usersRepository)

        initUsersView()
        initErrorView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_USER_REQ_CODE) {
            if (resultCode == EditUserActivity.USER_ADDED_RESULT) {
                presenter.onUsersListUpdated()
            }
        } else if (requestCode == EDIT_USER_REQ_CODE) {
            if (resultCode == EditUserActivity.USER_EDITED_RESULT) {
                presenter.onUsersListUpdated()
            } else if (resultCode == EditUserActivity.USER_DELETED_RESULT) {
                presenter.onUsersListUpdated()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)

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
        usersView.onItemClickedListener = { user ->
            presenter.onUserClicked(user)
        }

        usersView.onEmptyViewClickedListener = {
            presenter.onRefreshUsers()
        }

        usersView.onScrolledToNextPageListener = {
            presenter.onLoadNextUsersPage()
        }

        usersView.onSwipedToRefreshListener = {
            presenter.onRefreshUsers()
        }
    }

    private fun initErrorView() {
        errorView.setOnClickListener {
            presenter.onRefreshUsers()
        }
    }

    override fun showLoadIndicator() {
        renderView(showUsersView = true)
        usersView.setLoading()
    }

    override fun showUsers(users: List<User>, hasMore: Boolean) {
        renderView(showUsersView = true)
        usersView.setItems(users, hasMore)
    }

    override fun showInitialError() {
        renderView(showErrorText = true)
    }

    override fun showRefreshIndicator() {
        renderView(showUsersView = true)
        usersView.setRefreshing()
    }

    override fun showUserDetails(user: User) {
        startActivityForResult(EditUserActivity.createIntent(this, user), EDIT_USER_REQ_CODE)
    }

    override fun showError() {
        showDialog(R.string.users_error_dialog_title, R.string.users_error_message)
    }

    override fun showNewUserForm() {
        startActivityForResult(EditUserActivity.createIntene(this), ADD_USER_REQ_CODE)
    }

    private fun showDialog(titleRes: Int, messageRes: Int) {
        AlertDialog.Builder(this)
                .setTitle(titleRes)
                .setMessage(messageRes)
                .setPositiveButton(R.string.ok, null)
                .create()
                .show()
    }

    private fun renderView(
            showUsersView: Boolean = false,
            showErrorText: Boolean = false) {
        usersView.visible(showUsersView)
        errorView.visible(showErrorText)
        if (showErrorText) errorMessageText.setText(R.string.users_error_message)
    }

    companion object {

        private const val ADD_USER_REQ_CODE = 444
        private const val EDIT_USER_REQ_CODE = 445

        fun createIntent(context: Context) = Intent(context, UsersListActivity::class.java)
    }
}
