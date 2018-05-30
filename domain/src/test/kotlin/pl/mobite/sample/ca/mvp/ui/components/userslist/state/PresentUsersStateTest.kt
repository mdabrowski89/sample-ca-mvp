package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.utils.extensions.*

class PresentUsersStateTest: AbstractUsersListPresenterStateTest() {

    private val pageToLoadOnStartMock: Int by lazyPowerMock()
    private val pageMetadataMock: PageMetadata by lazyPowerMock()

    private var usersList: List<User> = listOf(createPowerMock(), createPowerMock())

    @Before
    override fun setUp() {
        super.setUp()

        whenever(presenterMock.users).thenReturn(usersList)
        whenever(presenterMock.pageMetadata).thenReturn(pageMetadataMock)
        whenever(pageMetadataMock.isLast).thenReturn(false)
    }

    @Test
    fun onApplied_lastPage() {
        whenever(pageMetadataMock.isLast).thenReturn(true)
        state = PresentUsersState(null)

        state.onApplied()

        verify(viewMock).showUsers(usersList, false)
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onApplied_notLastPage() {
        state = PresentUsersState(null)

        state.onApplied()

        verify(viewMock).showUsers(usersList, true)
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onApplied_onPageLoadOnStart() {
        val currentPage = 1
        val pageToLoad = currentPage + 1
        whenever(pageMetadataMock.index).thenReturn(currentPage)
        whenever(pageToLoadOnStartMock).thenReturn(pageToLoad)
        state = PresentUsersState(pageToLoadOnStartMock)

        state.onApplied()

        verify(viewMock).showUsers(usersList, true)
        verifyStateIs<LoadUsersPageState>()
        verifyOnState<LoadUsersPageState> { it.pageToLoad == pageToLoad }
    }

    @Test
    fun onUserClicked() {
        val userMock: User = createPowerMock()
        state = PresentUsersState(pageToLoadOnStartMock)

        state.onUserClicked(userMock)

        verify(viewMock).showUserDetails(userMock)
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onRefreshUsers() {
        state = PresentUsersState(pageToLoadOnStartMock)

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        val nextPageIndex: Int = createPowerMock()
        whenever(pageMetadataMock.nextIndex).thenReturn(nextPageIndex)
        state = PresentUsersState(pageToLoadOnStartMock)

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersPageState>()
        verifyOnState<LoadUsersPageState> { it.pageToLoad == nextPageIndex }
    }
}