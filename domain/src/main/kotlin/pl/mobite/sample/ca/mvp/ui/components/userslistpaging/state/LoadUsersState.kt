package pl.mobite.sample.ca.mvp.ui.components.userslistpaging.state

import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.ui.components.userslist.state.LoadInitialUsersPageState
import java.io.Serializable


class LoadUsersState : AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            setNewState(LoadUsersPageState(PageMetadata.FIRST_PAGE_INDEX))
        }
    }

    override fun createSavableInstance(): Serializable = LoadInitialUsersPageState()
}