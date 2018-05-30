package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions

class LoadUsersStateTest: AbstractUsersListPagingPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // nothing to set up
    }

    @Test
    fun onApplied() {
        state = LoadUsersState()

        state.onApplied()

        verify(viewMock).showLoadIndicator()
        verify(viewMock).loadUsers()
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onUserClicked() {
        val userMock: User = createPowerMock()
        state = LoadUsersState()

        state.onUserClicked(userMock)

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersState>()
    }

    @Test
    fun onRefreshUsers() {
        state = LoadUsersState()

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersState>()
    }
}