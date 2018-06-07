package pl.mobite.sample.ca.mvp.ui.components.userslistpaging

import org.junit.Before
import org.junit.Test
import org.powermock.core.classloader.annotations.PrepareForTest
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterTest
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.AbstractUsersListPagingPresenterState
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.lazyPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verify

@PrepareForTest(User::class)
class UsersListPagingPresenterTest : BasePresenterTest<UsersListPagingPresenter, UsersListPagingView>() {

    private val stateMock: AbstractUsersListPagingPresenterState by lazyPowerMock()

    @Before
    fun setUp() {
        init<UsersListPagingView>()
        presenter = UsersListPagingPresenter(viewMock)
        presenter.setNewState(stateMock)
    }

    @Test
    fun onUserClicked() {
        val user = createPowerMock<User>()
        presenter.onUserClicked(user)

        verify(stateMock).onUserClicked(user)
    }

    @Test
    fun onRefreshUsers() {
        presenter.onRefreshUsers()

        verify(stateMock).onRefreshUsers()
    }

    @Test
    fun onAddUserClicked() {
        presenter.onAddUserClicked()

        verify(stateMock).onAddUserClicked()
    }
}