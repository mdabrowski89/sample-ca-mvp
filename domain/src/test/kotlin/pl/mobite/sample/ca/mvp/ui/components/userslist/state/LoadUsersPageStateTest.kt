package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.utils.extensions.*

class LoadUsersPageStateTest: AbstractUsersListPresenterStateTest() {

    private val pageToLoadMock: Int by lazyPowerMock()
    private val repositoryMock: UsersRepository by lazyPowerMock()
    private val pageMetadataMock: PageMetadata by lazyPowerMock()
    private val newPageMock: Page<User> by lazyPowerMock()
    private val newPageMetadataMock: PageMetadata by lazyPowerMock()

    private val users = listOf<User>(createPowerMock(), createPowerMock())
    private val usersEmpty = listOf<User>()

    @Before
    override fun setUp() {
        super.setUp()

        whenever(presenterMock.usersRepository).thenReturn(repositoryMock)
        whenever(presenterMock.users).thenReturn(users)
        whenever(presenterMock.pageMetadata).thenReturn(pageMetadataMock)
        whenever(pageMetadataMock.isInitialPage).thenReturn(false)
        whenever(newPageMock.metadata).thenReturn(newPageMetadataMock)
    }

    @Test
    fun onApplied_onInitialPage() {
        whenever(repositoryMock.getUsersPage(any())).thenReturn(Single.never())
        whenever(pageMetadataMock.isInitialPage).thenReturn(true)
        state = LoadUsersPageState(pageToLoadMock)

        state.onApplied()

        verify(viewMock).showLoadIndicator()
        // we stayed in the same state, because in this test we are only testing the load/refresh indicator
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onApplied_onFirstPageWithNoUsers() {
        whenever(repositoryMock.getUsersPage(any())).thenReturn(Single.never())
        whenever(pageMetadataMock.isFirst).thenReturn(true)
        whenever(pageToLoadMock).thenReturn(PageMetadata.FIRST_PAGE_INDEX)
        whenever(presenterMock.users).thenReturn(usersEmpty)
        state = LoadUsersPageState(pageToLoadMock)

        state.onApplied()

        verify(viewMock).showLoadIndicator()
        // we stayed in the same state, because in this test we are only testing the load/refresh indicator
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onApplied_onFirstPageWithUsers() {
        whenever(repositoryMock.getUsersPage(any())).thenReturn(Single.never())
        whenever(pageMetadataMock.isFirst).thenReturn(true)
        whenever(pageToLoadMock).thenReturn(PageMetadata.FIRST_PAGE_INDEX)
        state = LoadUsersPageState(pageToLoadMock)

        state.onApplied()

        verify(viewMock).showRefreshIndicator()
        // we stayed in the same state, because in this test we are only testing the load/refresh indicator
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onApplied_onNextPage() {
        whenever(repositoryMock.getUsersPage(any())).thenReturn(Single.never())
        state = LoadUsersPageState(pageToLoadMock)

        state.onApplied()

        verify(viewMock).showRefreshIndicator()
        // we stayed in the same state, because in this test we are only testing the load/refresh indicator
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onApplied_onSuccess() {
        whenever(repositoryMock.getUsersPage(any())).thenReturn(Single.just(newPageMock))
        state = LoadUsersPageState(pageToLoadMock)

        state.onApplied()

        verifyStateIs<MergeUsersPageState>()
    }

    @Test
    fun onApplied_onError() {
        whenever(repositoryMock.getUsersPage(any())).thenReturn(Single.error(Exception()))
        state = LoadUsersPageState(pageToLoadMock)

        state.onApplied()

        verifyStateIs<DisplayErrorState>()
    }

    @Test
    fun onUserClicked() {
        val userMock: User = createPowerMock()
        state = LoadUsersPageState(pageToLoadMock)

        state.onUserClicked(userMock)

        verify(viewMock).showUserDetails(userMock)
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onRefreshUsers() {
        state = LoadUsersPageState(pageToLoadMock)

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = LoadUsersPageState(pageToLoadMock)

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadUsersPageState>()
    }

    @Test
    fun onAddUserClicked() {
        state = LoadUsersPageState(pageToLoadMock)

        state.onAddUserClicked()

        verify(viewMock).showNewUserForm()
        verifyStateIs<LoadUsersPageState>()
    }
}