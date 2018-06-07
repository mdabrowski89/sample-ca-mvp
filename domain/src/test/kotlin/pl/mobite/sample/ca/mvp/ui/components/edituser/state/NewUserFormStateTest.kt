package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions


class NewUserFormStateTest: AbstractEditUserPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // do nothing
    }

    @Test
    fun onApplied() {
        state = NewUserFormState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onUpdateUser() {
        state = NewUserFormState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onCreateUser_missingName() {
        state = NewUserFormState()

        state.onCreateUser(userFormData.copy(name = null))

        verify(viewMock).showInvalidUserData()
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onCreateUser_emptyName() {
        state = NewUserFormState()

        state.onCreateUser(userFormData.copy(name = ""))

        verify(viewMock).showInvalidUserData()
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onCreateUser_blankName() {
        state = NewUserFormState()

        state.onCreateUser(userFormData.copy(name = "  "))

        verify(viewMock).showInvalidUserData()
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onCreateUser_missingAge() {
        state = NewUserFormState()

        state.onCreateUser(userFormData.copy(age = null))

        verify(viewMock).showInvalidUserData()
        verifyStateIs<NewUserFormState>()
    }

    @Test
    fun onCreateUser_validFormData() {
        state = NewUserFormState()

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<CreateUserState>()
    }

    @Test
    fun onDeleteUser() {
        state = NewUserFormState()

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<NewUserFormState>()
    }
}