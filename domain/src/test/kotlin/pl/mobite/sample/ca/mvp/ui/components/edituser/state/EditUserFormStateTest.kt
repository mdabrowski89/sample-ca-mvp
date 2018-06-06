package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions
import pl.mobite.sample.ca.mvp.utils.extensions.whenever


class EditUserFormStateTest: AbstractEditUserPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // do nothing
    }

    @Test
    fun onApplied() {
        state = EditUserFormState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<EditUserFormState>()
    }

    @Test
    fun onUpdateUser_missingUser() {
        whenever(presenterMock.user).thenReturn(null)
        state = EditUserFormState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<ShowErrorAndCloseState>()
    }

    @Test
    fun onUpdateUser_missingName() {
        whenever(presenterMock.user).thenReturn(user)
        state = EditUserFormState()

        state.onUpdateUser(userFormData.copy(name = null))

        verify(viewMock).showInvalidUserData()
        verifyStateIs<EditUserFormState>()
    }

    @Test
    fun onUpdateUser_missingAge() {
        whenever(presenterMock.user).thenReturn(user)
        state = EditUserFormState()

        state.onUpdateUser(userFormData.copy(age = null))

        verify(viewMock).showInvalidUserData()
        verifyStateIs<EditUserFormState>()
    }

    @Test
    fun onUpdateUser_validFormData() {
        whenever(presenterMock.user).thenReturn(user)
        state = EditUserFormState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<UpdateUserState>()
    }

    @Test
    fun onCreateUser() {
        state = EditUserFormState()

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<EditUserFormState>()
    }

    @Test
    fun onDeleteUser() {
        state = EditUserFormState()

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DeleteUserState>()
    }
}