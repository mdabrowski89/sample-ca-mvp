package pl.mobite.sample.ca.mvp.ui.components.home

import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.Shadows
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.ui.base.BaseViewTest
import pl.mobite.sample.ca.mvp.ui.components.userslist.UsersListActivity
import pl.mobite.sample.ca.mvp.utils.extensions.assertNotNull
import pl.mobite.sample.ca.mvp.utils.extensions.assertTrue
import pl.mobite.sample.ca.mvp.utils.extensions.verify
import pl.mobite.sample.ca.mvp.utils.extensions.whenever


class HomeActivityTest: BaseViewTest<HomeActivity, HomePresenter>() {

    @Before
    fun setUp() {
        init<HomePresenter>()
        view = Robolectric.setupActivity(HomeActivity::class.java)
        view.presenter = presenterMock
        whenever(presenterMock.view).thenReturn(view)
    }

    @Test
    fun setUpView() {
        assertNotNull(view)
        assertNotNull(view.showUsersButton)

        assertTrue(view.showUsersButton.visibility == View.VISIBLE)
        assertTrue(view.showUsersButton.text == view.getString(R.string.main_show_users))
    }

    @Test
    fun onClickShowUsersButton() {
        view.showUsersButton.performClick()

        verify(presenterMock).onShowUsersClicked()
    }

    @Test
    fun showUsersList() {
        view.showUsersList()

        val startedComponent = Shadows.shadowOf(view).nextStartedActivity.component
        val expectedComponent = UsersListActivity.createIntent(view).component
        assertTrue(startedComponent == expectedComponent)
    }

}