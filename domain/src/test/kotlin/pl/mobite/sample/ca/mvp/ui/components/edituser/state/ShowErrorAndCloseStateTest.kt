package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions


class ShowErrorAndCloseStateTest: AbstractEditUserPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // do nothing
    }

    @Test
    fun onApplied() {
        state = ShowErrorAndCloseState()

        state.onApplied()

        verify(viewMock).showAppError()
        verify(viewMock).close()
        verifyStateIs<ShowErrorAndCloseState>()
    }

    @Test
    fun onUpdateUser() {
        state = ShowErrorAndCloseState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<ShowErrorAndCloseState>()
    }

    @Test
    fun onCreateUser() {
        state = ShowErrorAndCloseState()

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<ShowErrorAndCloseState>()
    }

    @Test
    fun onDeleteUser() {
        state = ShowErrorAndCloseState()

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<ShowErrorAndCloseState>()
    }
}