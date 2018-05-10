package pl.mobite.sample.ca.mvp.ui.components.home

import pl.mobite.sample.ca.mvp.ui.base.Presenter


class HomePresenter(view: HomeView): Presenter<HomeView>(view) {

    fun onShowUsersClicked() {
        view.showUsersList()
    }
}