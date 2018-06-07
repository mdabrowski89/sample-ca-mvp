package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions

class DisplayInitialErrorStateTest: AbstractUsersListPresenterStateTest() {

    @Before
    override fun setUp() {
        super.setUp()
        // nothing to set up
    }

    @Test
    fun onApplied_onError() {
        state = DisplayInitialErrorState()

        state.onApplied()

        verify(viewMock).showInitialError()
        verifyStateIs<DisplayInitialErrorState>()
    }

    @Test
    fun onRefreshUsers() {
        state = DisplayInitialErrorState()

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

    @Test
    fun onUserClicked() {
        state = DisplayInitialErrorState()

        state.onUserClicked(createPowerMock())

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayInitialErrorState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = DisplayInitialErrorState()

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayInitialErrorState>()
    }

    @Test
    fun onAddUserClicked() {
        state = DisplayInitialErrorState()

        state.onAddUserClicked()

        verify(viewMock).showNewUserForm()
        verifyStateIs<DisplayInitialErrorState>()
    }
}