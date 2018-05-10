package pl.mobite.sample.ca.mvp.ui.components.userlist.state

import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.User
import java.io.Serializable


class MergeUsersPageState(
        private val newPage: Page<User>
) : AbstractUsersListPresenterState() {

    override fun onApplied() {
        with(presenter) {
            with(newPage) {
                users = if (metadata.isFirst) {
                    data
                } else {
                    users.plus(data)
                }
                pageMetadata = metadata
            }
        }
        presenter.setNewState(PresentUsersState())
    }

    override fun createSavableInstance(): Serializable = MergeUsersPageState(newPage)
}