package pl.mobite.sample.ca.mvp.ui.components.userslist.state


class DisplayErrorState: AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            /** If error occurred when data are loaded for the first time show full screen error
             *  If error occurred when data are refreshed or next page is loaded, show different error (dialog or toast) */
            if (pageMetadata.isInitialPage) {
                setNewState(DisplayInitialErrorState())
                return
            }
            view.showError()
            setNewState(PresentUsersState())
        }
    }

    override fun createSavableInstance() = PresentUsersState()

}