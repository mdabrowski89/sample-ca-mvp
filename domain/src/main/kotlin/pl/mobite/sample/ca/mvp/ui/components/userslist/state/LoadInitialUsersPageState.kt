package pl.mobite.sample.ca.mvp.ui.components.userslist.state

import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import java.io.Serializable


class LoadInitialUsersPageState : AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            setNewState(LoadUsersPageState(PageMetadata.FIRST_PAGE_INDEX))
        }
    }

    override fun createSavableInstance(): Serializable = LoadInitialUsersPageState()
}