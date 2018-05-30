package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import org.junit.Before
import org.powermock.core.classloader.annotations.PrepareForTest
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterStateTest
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.UsersListPagingPresenter
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.UsersListPagingView


@PrepareForTest(UsersListPagingPresenter::class, User::class)
abstract class AbstractUsersListPagingPresenterStateTest
    : BasePresenterStateTest<AbstractUsersListPagingPresenterState, UsersListPagingPresenter, UsersListPagingView>() {

    @Before
    override fun setUp() {
        init<UsersListPagingPresenter, UsersListPagingView>()
        super.setUp()
    }
}