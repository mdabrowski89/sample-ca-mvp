package pl.mobite.sample.ca.mvp.ui.components.home

import org.junit.Before
import org.junit.Test
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterTest
import pl.mobite.sample.ca.mvp.utils.extensions.verify

class HomePresenterTest: BasePresenterTest<HomePresenter, HomeView>() {

    @Before
    fun setUp() {
        init<HomeView>()
        presenter = HomePresenter(viewMock)
    }

    @Test
    fun onShowUsersClicked() {
        presenter.onShowUsersClicked()

        verify(viewMock).showUsersList()
    }

    @Test
    fun onShowUsersWithPagingClicked() {
        presenter.onShowUsersWithPagingClicked()

        verify(viewMock).showUsersListPaging()
    }

}