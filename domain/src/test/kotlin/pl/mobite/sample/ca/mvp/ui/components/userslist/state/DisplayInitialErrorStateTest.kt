package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.RepositoryErrorType
import pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.LoadUsersState
import pl.mobite.sample.ca.mvp.utils.extensions.*

class DisplayInitialErrorStateTest: AbstractUsersListPresenterStateTest() {

    private val errorTypeMock: RepositoryErrorType by lazyPowerMock()

    @Before
    override fun setUp() {
        super.setUp()
        // nothing to set up
    }

    @Test
    fun onApplied_onServerError() {
        setUpEnumMock(errorTypeMock, RepositoryErrorType.SERVER)
        state = DisplayInitialErrorState(errorTypeMock)

        state.onApplied()

        verify(viewMock).showInitialServerError()
        verifyStateIs<pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.DisplayInitialErrorState>()
    }

    @Test
    fun onApplied_onNetworkError() {
        setUpEnumMock(errorTypeMock, RepositoryErrorType.NETWORK)
        state = DisplayInitialErrorState(errorTypeMock)

        state.onApplied()

        verify(viewMock).showInitialNetworkError()
        verifyStateIs<pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.DisplayInitialErrorState>()
    }

    @Test
    fun onRefreshUsers() {
        state = DisplayInitialErrorState(errorTypeMock)

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersState>()
    }

    @Test
    fun onUserClicked() {
        state = DisplayInitialErrorState(errorTypeMock)

        state.onUserClicked(createPowerMock())

        verifyZeroInteractions(viewMock)
        verifyStateIs<pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.DisplayInitialErrorState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = DisplayInitialErrorState(errorTypeMock)

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state.DisplayInitialErrorState>()
    }

}