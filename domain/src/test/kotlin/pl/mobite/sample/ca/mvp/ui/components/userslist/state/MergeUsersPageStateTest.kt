package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.ui.components.userlist.state.MergeUsersPageState
import pl.mobite.sample.ca.mvp.ui.components.userlist.state.PresentUsersState
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.lazyPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verifyZeroInteractions
import pl.mobite.sample.ca.mvp.utils.extensions.whenever

class MergeUsersPageStateTest: AbstractUsersListPresenterStateTest() {

    private val newPageMock: Page<User> by lazyPowerMock()
    private val pageMetadataMock: PageMetadata by lazyPowerMock()

    private val newUsersList: List<User> = listOf(createPowerMock(), createPowerMock())

    @Before
    override fun setUp() {
        super.setUp()

        whenever(newPageMock.data).thenReturn(newUsersList)
        whenever(newPageMock.metadata).thenReturn(pageMetadataMock)
        whenever(pageMetadataMock.isFirst).thenReturn(false)
    }

    @Test
    fun onApplied_onFirstPage() {
        whenever(pageMetadataMock.isFirst).thenReturn(true)
        state = MergeUsersPageState(newPageMock)

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onApplied() {
        state = MergeUsersPageState(newPageMock)

        state.onApplied()

        verifyZeroInteractions(viewMock)
        verifyStateIs<PresentUsersState>()
    }

    @Test
    fun onUserClicked() {
        state = MergeUsersPageState(newPageMock)

        state.onUserClicked(createPowerMock())

        verifyZeroInteractions(viewMock)
        verifyStateIs<MergeUsersPageState>()
    }

    @Test
    fun onRefreshUsers() {
        state = MergeUsersPageState(newPageMock)

        state.onRefreshUsers()

        verifyZeroInteractions(viewMock)
        verifyStateIs<MergeUsersPageState>()
    }

    @Test
    fun onLoadNextUsersPage() {
        state = MergeUsersPageState(newPageMock)

        state.onLoadNextUsersPage()

        verifyZeroInteractions(viewMock)
        verifyStateIs<MergeUsersPageState>()
    }
}