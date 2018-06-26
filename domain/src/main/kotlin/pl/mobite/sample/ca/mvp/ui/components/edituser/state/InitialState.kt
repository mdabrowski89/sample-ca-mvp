package pl.mobite.sample.ca.mvp.ui.components.edituser.state


class InitialState: AbstractEditUserPresenterState() {

    override fun onApplied() {
        with(presenter) {
            if (userId == null) {
                setNewState(NewUserFormState())
            } else {
                setNewState(LoadUserState())
            }
        }
    }

    override fun createSavableInstance() = InitialState()
}