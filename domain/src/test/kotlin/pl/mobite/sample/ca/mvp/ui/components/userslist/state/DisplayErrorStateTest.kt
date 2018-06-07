package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.utils.extensions.*


class DisplayErrorStateTest: AbstractUsersListPresenterStateTest() {

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
        state = DisplayErrorState()

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayInitialErrorState>()
    }

    @Test
    fun onApplied_onError() {
        state = DisplayErrorState()

        state.onApplied()

        verify(viewMock).showError()
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onUserClicked() {
        state = DisplayErrorState()

        state.onUserClicked(createPowerMock())

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayErrorState>()
    }

    @Test
    fun onRefreshUsers() {
        state = DisplayErrorState()

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayErrorState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = DisplayErrorState()

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayErrorState>()
    }

    @Test
    fun onAddUserClicked() {
        state = DisplayErrorState()

        state.onAddUserClicked()

        verifyZeroInteractions(viewMock)
        verifyStateIs<DisplayErrorState>()
    }
}