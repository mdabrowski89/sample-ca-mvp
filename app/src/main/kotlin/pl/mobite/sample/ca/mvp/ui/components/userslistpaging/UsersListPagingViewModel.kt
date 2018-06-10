package pl.mobite.sample.ca.mvp.ui.components.userslistpaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import pl.mobite.sample.ca.mvp.MyApp
import pl.mobite.sample.ca.mvp.data.local.room.UserDao
import pl.mobite.sample.ca.mvp.data.local.room.UserEntity
import javax.inject.Inject


class UsersListPagingViewModel: ViewModel() {

    @Inject lateinit var userDao: UserDao

    val users: LiveData<PagedList<UserEntity>>

    init {
        MyApp.instance.appComponent.inject(this)
        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .setInitialLoadSizeHint(20)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()
        val factory = userDao.getAllUsers()
        users = LivePagedListBuilder(factory, config).build()
    }
}