package pl.mobite.sample.ca.mvp.ui.components.userlist.state

import pl.mobite.sample.ca.mvp.data.models.RepositoryErrorType


class DisplayErrorState(
        val repositoryErrorType: RepositoryErrorType
): AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            /** If error occurred when data are loaded for the first time show full screen error
             *  If error occurred when data are refreshed or next page is loaded, show different error (dialog or toast) */
            if (pageMetadata.isInitialPage) {
                setNewState(DisplayInitialErrorState(repositoryErrorType))
                return
            }
            when (repositoryErrorType) {
                RepositoryErrorType.NETWORK -> view.showNetworkError()
                RepositoryErrorType.SERVER -> view.showServerError()
            }
            setNewState(PresentUsersState())
        }
    }

    override fun createSavableInstance() = PresentUsersState()

}