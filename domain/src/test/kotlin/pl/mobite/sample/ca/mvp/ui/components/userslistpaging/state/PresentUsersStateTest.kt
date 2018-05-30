package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions

class PresentUsersStateTest: AbstractUsersListPagingPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // nothing to set up
    }

    @Test
    fun onApplied() {
        state = PresentUsersState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onUserClicked() {
        val userMock: User = createPowerMock()
        state = PresentUsersState()

        state.onUserClicked(userMock)

        verify(viewMock).showUserDetails(userMock)
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onRefreshUsers() {
        state = PresentUsersState()

        state.onRefreshUsers()

        verify(viewMock).showRefreshIndicator()
        verify(viewMock).refreshUsers()
        verifyStateIs<PresentUsersState>()
    }
}