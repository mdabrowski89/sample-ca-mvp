package pl.mobite.sample.ca.mvp.ui.components.edituser.state


class FinalState: AbstractEditUserPresenterState() {

    override fun onApplied() {
        with(presenter) {
            view.close()
        }
    }

    override fun createSavableInstance() = FinalState()
}