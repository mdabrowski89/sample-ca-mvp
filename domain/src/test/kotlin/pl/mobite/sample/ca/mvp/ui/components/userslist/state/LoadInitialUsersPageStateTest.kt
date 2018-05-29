package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.LoadUsersState
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.LoadUsersPageState
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions

class LoadInitialUsersPageStateTest: AbstractUsersListPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // nothing to set up
    }
    @Test
    fun onApplied() {
        state = LoadUsersState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onUserClicked() {
        state = LoadUsersState()

        state.onUserClicked(createPowerMock())

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

    @Test
    fun onLoadNextUsersPage() {
        state = LoadUsersState()

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersState>()
    }

}