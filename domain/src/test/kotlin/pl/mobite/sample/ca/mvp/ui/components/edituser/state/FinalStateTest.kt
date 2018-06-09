package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions


class FinalStateTest: AbstractEditUserPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // do nothing
    }

    @Test
    fun onApplied() {
        state = FinalState()

        state.onApplied()

        verify(viewMock).close()
        verifyStateIs<FinalState>()
    }

    @Test
    fun onUpdateUser() {
        state = FinalState()

        state.onUpdateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<FinalState>()
    }

    @Test
    fun onCreateUser() {
        state = FinalState()

        state.onCreateUser(userFormData)

        verifyZeroInteractions(viewMock)
        verifyStateIs<FinalState>()
    }

    @Test
    fun onDeleteUser() {
        state = FinalState()

        state.onDeleteUser()

        verifyZeroInteractions(viewMock)
        verifyStateIs<FinalState>()
    }
}