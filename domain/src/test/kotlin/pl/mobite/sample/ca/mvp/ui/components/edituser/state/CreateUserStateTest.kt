package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.utils.extensions.*


class CreateUserStateTest: AbstractEditUserPresenterStateTest() {

    private val repositoryMock: UsersRepository by lazyPowerMock()

    @Before
    override fun setUp() {
        super.setUp()

        whenever(presenterMock.usersRepository).thenReturn(repositoryMock)
    }

    @Test
    fun onApplied() {
        whenever(repositoryMock.createUser(any())).thenReturn(Completable.never())
        state = CreateUserState(user)

        state.onApplied()

        verify(viewMock).showCreateIndicator()
        verifyStateIs<CreateUserState>()
    }

    @Test
    fun onApplied_onComplete() {
        whenever(repositoryMock.createUser(any())).thenReturn(Completable.complete())
        state = CreateUserState(user)

        state.onApplied()

        verify(viewMock).onCreateSuccess()
        verifyStateIs<FinalState>()
    }

    @Test
    fun onApplied_onError() {
        whenever(repositoryMock.createUser(any())).thenReturn(Completable.error(Throwable()))
        state = CreateUserState(user)

        state.onApplied()

        verify(viewMock).showCreateError()
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onUpdateUser() {
        state = CreateUserState(user)

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<CreateUserState>()
    }

    @Test
    fun onCreateUser() {
        state = CreateUserState(user)

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<CreateUserState>()
    }

    @Test
    fun onDeleteUser() {
        state = CreateUserState(user)

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<CreateUserState>()
    }
}