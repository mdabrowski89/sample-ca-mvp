package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
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
        state = LoadInitialUsersPageState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onUserClicked() {
        state = LoadInitialUsersPageState()

        state.onUserClicked(createPowerMock())

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

    @Test
    fun onRefreshUsers() {
        state = LoadInitialUsersPageState()

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = LoadInitialUsersPageState()

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

    @Test
    fun onAddUserClicked() {
        state = LoadInitialUsersPageState()

        state.onAddUserClicked()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

}