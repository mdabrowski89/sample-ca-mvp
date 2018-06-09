package pl.mobite.sample.ca.mvp.ui.components.edituser.state


class ShowErrorAndCloseState: AbstractEditUserPresenterState() {

    override fun onApplied() {
        with(presenter) {
            view.showAppError()
            view.close()
        }
    }

    override fun createSavableInstance() = ShowErrorAndCloseState()
}