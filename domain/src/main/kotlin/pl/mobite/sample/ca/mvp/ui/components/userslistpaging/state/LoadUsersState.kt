package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import java.io.Serializable


class LoadUsersState : AbstractUsersListPagingPresenterState() {

    override fun onApplied() {
        with(presenter) {
            view.showLoadIndicator()
            view.loadUsers()
            setNewState(PresentUsersState())
        }
    }

    override fun createSavableInstance(): Serializable = LoadUsersState()
}