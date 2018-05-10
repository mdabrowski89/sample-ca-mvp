package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.RepositoryErrorType
import pl.mobite.sample.ca.mvp.ui.components.userlist.state.DisplayErrorState
import pl.mobite.sample.ca.mvp.ui.components.userlist.state.DisplayInitialErrorState
import pl.mobite.sample.ca.mvp.ui.components.userlist.state.PresentUsersState
import pl.mobite.sample.ca.mvp.utils.extensions.*


class DisplayErrorStateTest: AbstractUsersListPresenterStateTest() {

    private val errorTypeMock: RepositoryErrorType by lazyPowerMock()
    private val pageMetadataMock: PageMetadata by lazyPowerMock()

    @Before
    override fun setUp() {
        super.setUp()

        whenever(presenterMock.pageMetadata).thenReturn(pageMetadataMock)
        whenever(pageMetadataMock.isInitialPage).thenReturn(false)
    }

    @Test
    fun onApplied_onInitialPage() {
        whenever(pageMetadataMock.isInitialPage).thenReturn(true)
        state = DisplayErrorState(errorTypeMock)

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayInitialErrorState>()
    }

    @Test
    fun onApplied_onNetworkError() {
        setUpEnumMock(errorTypeMock, RepositoryErrorType.NETWORK)
        state = DisplayErrorState(errorTypeMock)

        state.onApplied()

        verify(viewMock).showNetworkError()
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onApplied_onServerError() {
        setUpEnumMock(errorTypeMock, RepositoryErrorType.SERVER)
        state = DisplayErrorState(errorTypeMock)

        state.onApplied()

        verify(viewMock).showServerError()
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onUserClicked() {
        state = DisplayErrorState(errorTypeMock)

        state.onUserClicked(createPowerMock())

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayErrorState>()
    }

    @Test
    fun onRefreshUsers() {
        state = DisplayErrorState(errorTypeMock)

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayErrorState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = DisplayErrorState(errorTypeMock)

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayErrorState>()
    }
}