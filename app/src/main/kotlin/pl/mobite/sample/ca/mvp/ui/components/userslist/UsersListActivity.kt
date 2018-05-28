package pl.mobite.sample.ca.mvp.ui.components.userslist


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_users_list.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.activity.BasePresenterActivity
import pl.mobite.sample.ca.mvp.utils.Baker
import pl.mobite.sample.ca.mvp.utils.extensions.appComponent
import pl.mobite.sample.ca.mvp.utils.extensions.visible
import javax.inject.Inject

class UsersListActivity : BasePresenterActivity<UsersListPresenter>(), UsersListView {

    @Inject lateinit var usersRepository: UsersRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users_list)

        appComponent.inject(this)

        presenter = UsersListPresenter(this, usersRepository)

        initUsersView()
        initErrorView()
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

    override fun showInitialNetworkError() {
        renderView(showNetworkErrorText = true)
    }

    override fun showInitialServerError() {
        renderView(showServerErrorText = true)
    }

    override fun showRefreshIndicator() {
        renderView(showUsersView = true)
        usersView.setRefreshing()
    }

    override fun showUserDetails(user: User) {
        // TODO: implement details screen
        Baker.toast("not implemented")
    }

    override fun showServerError() {
        showDialog(R.string.users_error_dialog_title, R.string.users_error_server_message)
    }

    override fun showNetworkError() {
        showDialog(R.string.users_error_dialog_title, R.string.users_error_network_message)
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
            showServerErrorText: Boolean = false,
            showNetworkErrorText: Boolean = false) {
        usersView.visible(showUsersView)
        errorView.visible(showServerErrorText || showNetworkErrorText)
        if (showServerErrorText) errorMessageText.setText(R.string.users_error_server_message)
        if (showNetworkErrorText) errorMessageText.setText(R.string.users_error_network_message)
    }

    companion object {

        fun createIntent(context: Context) = Intent(context, UsersListActivity::class.java)
    }
}
