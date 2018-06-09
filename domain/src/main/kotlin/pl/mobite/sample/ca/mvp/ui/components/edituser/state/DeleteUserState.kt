package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.disposables.Disposable


class DeleteUserState: AbstractEditUserPresenterState() {

    private var disposable: Disposable? = null

    override fun onApplied() {
        with(presenter) {
            user?.let {
                view.showDeleteIndicator()
                disposable = usersRepository.deleteUser(it).subscribe(
                        /** onComplete */
                        {
                            view.showDeleteSuccess()
                            setNewState(FinalState())
                        },
                        /** onError */
                        { _: Throwable? ->
                            view.showDeleteError()
                            setNewState(EditUserFormState())
                        }
                )
            } ?: setNewState(ShowErrorAndCloseState())
        }
    }

    override fun onLeft(finished: Boolean) {
        disposable?.dispose()
    }

    override fun createSavableInstance() = EditUserFormState()
}