package pl.mobite.sample.ca.mvp.ui.components.userslist

import org.junit.Before
import org.junit.Test
import org.powermock.core.classloader.annotations.PrepareForTest
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterTest
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.AbstractUsersListPresenterState
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.lazyPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verify

@PrepareForTest(User::class)
class UsersListPresenterTest : BasePresenterTest<UsersListPresenter, UsersListView>() {

    private val usersRepositoryMock: UsersRepository by lazyPowerMock()
    private val stateMock: AbstractUsersListPresenterState by lazyPowerMock()

    @Before
    fun setUp() {
        init<UsersListView>()
        presenter = UsersListPresenter(viewMock, usersRepositoryMock)
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
    fun onLoadNextUsersPage() {
        presenter.onLoadNextUsersPage()

        verify(stateMock).onLoadNextUsersPage()
    }

    @Test
    fun onAddUserClicked() {
        presenter.onAddUserClicked()

        verify(stateMock).onAddUserClicked()
    }
}