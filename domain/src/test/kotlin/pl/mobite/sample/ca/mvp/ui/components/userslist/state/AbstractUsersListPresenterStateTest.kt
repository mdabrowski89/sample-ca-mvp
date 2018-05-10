package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import io.reactivex.Single
import org.junit.Before
import org.powermock.core.classloader.annotations.PrepareForTest
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.RepositoryErrorType
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterStateTest
import pl.mobite.sample.ca.mvp.ui.components.userlist.UsersListPresenter
import pl.mobite.sample.ca.mvp.ui.components.userlist.UsersListView
import pl.mobite.sample.ca.mvp.ui.components.userlist.state.AbstractUsersListPresenterState


@PrepareForTest(UsersListPresenter::class, PageMetadata::class, RepositoryErrorType::class, Page::class, User::class,
        Int::class, Boolean::class, Single::class)
abstract class AbstractUsersListPresenterStateTest: BasePresenterStateTest<AbstractUsersListPresenterState, UsersListPresenter, UsersListView>() {

    @Before
    override fun setUp() {
        init<UsersListPresenter, UsersListView>()
        super.setUp()
    }
}