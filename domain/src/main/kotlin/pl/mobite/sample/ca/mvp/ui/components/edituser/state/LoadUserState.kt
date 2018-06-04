package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.disposables.Disposable


class LoadUserState(userId: Long): AbstractEditUserPresenterState() {

    private var disposable: Disposable? = null

    override fun onApplied() {
        super.onApplied()
    }

    override fun onLeft(finished: Boolean) {
        super.onLeft(finished)
    }

    override fun createSavableInstance() = LoadUserState()
}