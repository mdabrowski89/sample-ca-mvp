package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.utils.extensions.*


class UpdateUserStateTest: AbstractEditUserPresenterStateTest() {

    private val repositoryMock: UsersRepository by lazyPowerMock()

    @Before
    override fun setUp() {
        super.setUp()

        whenever(presenterMock.usersRepository).thenReturn(repositoryMock)
    }

    @Test
    fun onApplied() {
        whenever(repositoryMock.updateUser(any())).thenReturn(Completable.never())
        state = UpdateUserState(user)

        state.onApplied()

        verify(viewMock).showUpdateIndicator()
        verifyStateIs<UpdateUserState>()
    }

    @Test
    fun onApplied_onComplete() {
        whenever(repositoryMock.updateUser(any())).thenReturn(Completable.complete())
        state = UpdateUserState(user)

        state.onApplied()

        verify(viewMock).onUpdateSuccess()
        verifyStateIs<FinalState>()
    }

    @Test
    fun onApplied_onError() {
        whenever(repositoryMock.updateUser(any())).thenReturn(Completable.error(Throwable()))
        state = UpdateUserState(user)

        state.onApplied()

        verify(viewMock).showUpdateError()
        verifyStateIs<EditUserFormState>()
    }

    @Test
    fun onUpdateUser() {
        state = UpdateUserState(user)

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<UpdateUserState>()
    }

    @Test
    fun onCreateUser() {
        state = UpdateUserState(user)

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<UpdateUserState>()
    }

    @Test
    fun onDeleteUser() {
        state = UpdateUserState(user)

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<UpdateUserState>()
    }
}