package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions
import pl.mobite.sample.ca.mvp.utils.extensions.whenever


class InitialStateTest: AbstractEditUserPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // do nothing
    }

    @Test
    fun onApplied_createUser() {
        whenever(presenterMock.userId).thenReturn(null)
        state = InitialState()

        state.onApplied()

        verify(viewMock).showNewUserForm()
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onApplied_editUser() {
        whenever(presenterMock.userId).thenReturn(1)
        state = InitialState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUserState>()
    }

    @Test
    fun onUpdateUser() {
        state = InitialState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<InitialState>()
    }

    @Test
    fun onCreateUser() {
        state = InitialState()

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<InitialState>()
    }

    @Test
    fun onDeleteUser() {
        state = InitialState()

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<InitialState>()
    }
}