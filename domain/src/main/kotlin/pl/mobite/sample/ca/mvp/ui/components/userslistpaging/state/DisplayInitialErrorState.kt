package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import pl.mobite.sample.ca.mvp.data.models.RepositoryErrorType
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.DisplayInitialErrorState


class DisplayInitialErrorState(
        private val repositoryErrorType: RepositoryErrorType
): AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            when (repositoryErrorType) {
                RepositoryErrorType.NETWORK -> view.showInitialNetworkError()
                RepositoryErrorType.SERVER -> view.showInitialServerError()
            }
        }
    }

    override fun onRefreshUsers() {
        with(presenter) {
            setNewState(LoadUsersState())
        }
    }

    override fun createSavableInstance() = DisplayInitialErrorState(repositoryErrorType)

}