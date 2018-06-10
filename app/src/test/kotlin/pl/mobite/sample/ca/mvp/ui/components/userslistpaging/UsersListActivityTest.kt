package pl.mobite.sample.ca.mvp.ui.components.userslistpaging

import kotlinx.android.synthetic.main.activity_users_list.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import pl.mobite.sample.ca.mvp.ui.base.BaseViewTest
import pl.mobite.sample.ca.mvp.utils.extensions.assertNotNull
import pl.mobite.sample.ca.mvp.utils.extensions.whenever


class UsersListActivityTest: BaseViewTest<UsersListPagingActivity, UsersListPagingPresenter>() {

    @Before
    fun setUp() {
        init<UsersListPagingPresenter>()
        view = Robolectric.setupActivity(UsersListPagingActivity::class.java)
        view.presenter = presenterMock
        whenever(presenterMock.view).thenReturn(view)
    }

    @Test
    fun setUpView() {
        assertNotNull(view)
        assertNotNull(view.usersView)
        assertNotNull(view.errorMessageText)
    }

    // TODO: add more tests
}