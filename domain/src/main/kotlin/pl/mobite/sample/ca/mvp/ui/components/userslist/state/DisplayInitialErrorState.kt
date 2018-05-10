package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import pl.mobite.sample.ca.mvp.data.models.RepositoryErrorType


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
            setNewState(LoadInitialUsersPageState())
        }
    }

    override fun createSavableInstance() = DisplayInitialErrorState(repositoryErrorType)

}