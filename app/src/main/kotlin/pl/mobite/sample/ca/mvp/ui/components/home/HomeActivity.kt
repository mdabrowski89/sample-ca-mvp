package pl.mobite.sample.ca.mvp.ui.components.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import pl.mobite.sample.ca.mvp.MyApp
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.local.room.UserEntity
import pl.mobite.sample.ca.mvp.ui.base.activity.BasePresenterActivity
import pl.mobite.sample.ca.mvp.ui.components.userslist.UsersListActivity
import pl.mobite.sample.ca.mvp.ui.components.userslist.withpaginglib.UsersListWithPagingLibActivity
import pl.mobite.sample.ca.mvp.utils.Baker

class HomeActivity : BasePresenterActivity<HomePresenter>(), HomeView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = HomePresenter(this)

        showUsersButton.setOnClickListener { presenter.onShowUsersClicked() }

        generateUsersButton.setOnClickListener {
            generateUsers()
        }

        showUsersWithPagingButton.setOnClickListener {
            showUsersListWitPaging()
        }
    }

    private fun generateUsers() {
        Completable
                .fromCallable {
                    val userDao = MyApp.instance.db.userDao()
                    userDao.deleteAll()
                    val users = mutableListOf<UserEntity>()
                    for(i in 1..73) {
                        users.add(UserEntity(null, "user $i", i))
                    }
                    userDao.insertAll(users)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete {
                    Baker.toast("Users generated")
                }.subscribe()
    }

    override fun showUsersList() {
        startActivity(UsersListActivity.createIntent(this))
    }

    fun showUsersListWitPaging() {
        startActivity(UsersListWithPagingLibActivity.createIntent(this))
    }

    companion object {

        fun createIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}
