package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.disposables.Disposable
import pl.mobite.sample.ca.mvp.data.models.User


class UpdateUserState(private val editedUser: User): AbstractEditUserPresenterState() {

    private var disposable: Disposable? = null

    override fun onApplied() {
        with(presenter) {
            view.showUpdateIndicator()
            disposable = usersRepository.updateUser(editedUser).subscribe(
                    /** onComplete */
                    {
                        view.onUpdateSuccess()
                        setNewState(FinalState())
                    },
                    /** onError */
                    { _: Throwable? ->
                        view.showUpdateError()
                        setNewState(EditUserFormState())
                    }
            )
        }
    }

    override fun onLeft(finished: Boolean) {
        disposable?.dispose()
    }

    override fun createSavableInstance() = EditUserFormState()
}