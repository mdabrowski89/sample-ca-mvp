package pl.mobite.sample.ca.mvp.ui.components.edituser.state


class InitialState(): AbstractEditUserPresenterState() {

    override fun onApplied() {
        with(presenter) {
            if (userId == null) {
                setNewState(PresentNewUserFormState())
            } else {
                setNewState(LoadUserState(userId))
            }
        }
    }

    override fun createSavableInstance() = InitialState()
}