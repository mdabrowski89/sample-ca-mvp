package pl.mobite.sample.ca.mvp.ui.components.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.ui.base.activity.BasePresenterActivity
import pl.mobite.sample.ca.mvp.ui.components.userslist.UsersListActivity

class HomeActivity : BasePresenterActivity<HomePresenter>(), HomeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = HomePresenter(this)

        showUsersButton.setOnClickListener { presenter.onShowUsersClicked() }
    }

    override fun showUsersList() {
        startActivity(UsersListActivity.createIntent(this))
    }

    companion object {

        fun createIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}
