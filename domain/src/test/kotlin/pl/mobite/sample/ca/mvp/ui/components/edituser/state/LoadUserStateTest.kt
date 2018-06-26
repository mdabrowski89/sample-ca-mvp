package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.utils.extensions.*


class LoadUserStateTest: AbstractEditUserPresenterStateTest() {

    private val repositoryMock: UsersRepository by lazyPowerMock()

    @Before
    override fun setUp() {
        super.setUp()

        whenever(presenterMock.usersRepository).thenReturn(repositoryMock)
        whenever(presenterMock.userId).thenReturn(1)
    }

    @Test
    fun onApplied_missingUserId() {
        whenever(presenterMock.userId).thenReturn(null)
        state = LoadUserState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<ShowErrorAndCloseState>()
    }

    @Test
    fun onApplied() {
        whenever(repositoryMock.getUser(any())).thenReturn(Single.never())
        state = LoadUserState()

        state.onApplied()

        verify(viewMock).showLoadIndicator()
        verifyStateIs<LoadUserState>()
    }

    @Test
    fun onApplied_onSuccess() {
        whenever(repositoryMock.getUser(any())).thenReturn(Single.just(user))
        state = LoadUserState()

        state.onApplied()

        verify(viewMock).setUser(user)
        verifyStateIs<EditUserFormState>()
    }

    @Test
    fun onApplied_onError() {
        whenever(repositoryMock.getUser(any())).thenReturn(Single.error(Throwable()))
        state = LoadUserState()

        state.onApplied()

        verify(viewMock).showLoadIndicator()
        verifyStateIs<ShowErrorAndCloseState>()
    }

    @Test
    fun onUpdateUser() {
        state = LoadUserState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUserState>()
    }

    @Test
    fun onCreateUser() {
        state = LoadUserState()

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUserState>()
    }

    @Test
    fun onDeleteUser() {
        state = LoadUserState()

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUserState>()
    }
}