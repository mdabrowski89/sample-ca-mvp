package pl.mobite.sample.ca.mvp.ui.components.userslist.state


class DisplayInitialErrorState: AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            view.showInitialError()
        }
    }

    override fun onRefreshUsers() {
        with(presenter) {
            setNewState(LoadInitialUsersPageState())
        }
    }

    override fun createSavableInstance() = DisplayInitialErrorState()

}