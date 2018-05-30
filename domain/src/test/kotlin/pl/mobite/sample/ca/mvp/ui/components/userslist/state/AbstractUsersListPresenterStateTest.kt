package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import io.reactivex.Single
import org.junit.Before
import org.powermock.core.classloader.annotations.PrepareForTest
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterStateTest
import pl.mobite.sample.ca.mvp.ui.components.userslist.UsersListPresenter
import pl.mobite.sample.ca.mvp.ui.components.userslist.UsersListView


@PrepareForTest(UsersListPresenter::class, PageMetadata::class, Page::class, User::class,
        Int::class, Boolean::class, Single::class)
abstract class AbstractUsersListPresenterStateTest: BasePresenterStateTest<AbstractUsersListPresenterState, UsersListPresenter, UsersListView>() {

    @Before
    override fun setUp() {
        init<UsersListPresenter, UsersListView>()
        super.setUp()
    }
}