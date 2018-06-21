package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.utils.extensions.*

class RefreshUsersPagesStateTest: AbstractUsersListPresenterStateTest() {

    private val pagesToLoadMock: Int by lazyPowerMock()
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
        whenever(repositoryMock.getUsersPages(any())).thenReturn(Single.never())
        whenever(pageMetadataMock.isInitialPage).thenReturn(true)
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onApplied()

        verify(viewMock).showLoadIndicator()
        // we stayed in the same state, because in this test we are only testing the load/refresh indicator
        verifyStateIs<RefreshUsersPagesState>()
    }

    @Test
    fun onApplied_onFirstPageWithNoUsers() {
        whenever(repositoryMock.getUsersPages(any())).thenReturn(Single.never())
        whenever(pageMetadataMock.isFirst).thenReturn(true)
        whenever(pagesToLoadMock).thenReturn(PageMetadata.FIRST_PAGE_NUMBER)
        whenever(presenterMock.users).thenReturn(usersEmpty)
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onApplied()

        verify(viewMock).showLoadIndicator()
        // we stayed in the same state, because in this test we are only testing the load/refresh indicator
        verifyStateIs<RefreshUsersPagesState>()
    }

    @Test
    fun onApplied_onSuccess() {
        whenever(repositoryMock.getUsersPages(any())).thenReturn(Single.just(listOf(newPageMock)))
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onApplied()

        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onApplied_onError() {
        whenever(repositoryMock.getUsersPages(any())).thenReturn(Single.error(Exception()))
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onApplied()

        verifyStateIs<DisplayErrorState>()
    }

    @Test
    fun onUserClicked() {
        val userMock: User = createPowerMock()
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onUserClicked(userMock)

        verify(viewMock).showUserDetails(userMock)
        verifyStateIs<RefreshUsersPagesState>()
    }

    @Test
    fun onRefreshUsers() {
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<LoadInitialUsersPageState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<RefreshUsersPagesState>()
    }

    @Test
    fun onAddUserClicked() {
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onAddUserClicked()

        verify(viewMock).showNewUserForm()
        verifyStateIs<RefreshUsersPagesState>()
    }

    @Test
    fun onUsersListUpdated() {
        state = RefreshUsersPagesState(pagesToLoadMock)

        state.onUsersListUpdated()

        verifyZeroInteractions(viewMock)
        verifyStateIs<RefreshUsersPagesState>()
    }
}