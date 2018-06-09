package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.utils.extensions.*


class DeleteUserStateTest: AbstractEditUserPresenterStateTest() {

    private val repositoryMock: UsersRepository by lazyPowerMock()

    @Before
    override fun setUp() {
        super.setUp()

        whenever(presenterMock.usersRepository).thenReturn(repositoryMock)
        whenever(presenterMock.user).thenReturn(user)
    }

    @Test
    fun onApplied_missingUser() {
        whenever(presenterMock.user).thenReturn(null)
        state = DeleteUserState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<ShowErrorAndCloseState>()
    }

    @Test
    fun onApplied() {
        whenever(repositoryMock.deleteUser(any())).thenReturn(Completable.never())
        state = DeleteUserState()

        state.onApplied()

        verify(viewMock).showDeleteIndicator()
        verifyStateIs<DeleteUserState>()
    }

    @Test
    fun onApplied_onComplete() {
        whenever(repositoryMock.deleteUser(any())).thenReturn(Completable.complete())
        state = DeleteUserState()

        state.onApplied()

        verify(viewMock).showDeleteSuccess()
        verifyStateIs<FinalState>()
    }

    @Test
    fun onApplied_onError() {
        whenever(repositoryMock.deleteUser(any())).thenReturn(Completable.error(Throwable()))
        state = DeleteUserState()

        state.onApplied()

        verify(viewMock).showDeleteError()
        verifyStateIs<EditUserFormState>()
    }

    @Test
    fun onUpdateUser() {
        state = DeleteUserState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<DeleteUserState>()
    }

    @Test
    fun onCreateUser() {
        state = DeleteUserState()

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<DeleteUserState>()
    }

    @Test
    fun onDeleteUser() {
        state = DeleteUserState()

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DeleteUserState>()
    }
}