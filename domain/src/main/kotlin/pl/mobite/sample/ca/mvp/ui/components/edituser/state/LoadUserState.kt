package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.disposables.Disposable
import pl.mobite.sample.ca.mvp.data.models.User


class LoadUserState: AbstractEditUserPresenterState() {

    private var disposable: Disposable? = null

    override fun onApplied() {
        with(presenter) {
            userId?.let {
                view.showLoadIndicator()
                disposable = usersRepository.getUser(it).subscribe(
                        /** onSuccess */
                        { user: User ->
                            this.user = user
                            view.showUserForm(user)
                            setNewState(EditUserFormState())
                        },
                        /** onError */
                        { _: Throwable? ->
                            setNewState(ShowErrorAndCloseState())
                        }
                )
            } ?: setNewState(ShowErrorAndCloseState())
        }
    }

    override fun onLeft(finished: Boolean) {
        disposable?.dispose()
    }

    override fun createSavableInstance() = LoadUserState()
}