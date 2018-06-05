package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.disposables.Disposable
import pl.mobite.sample.ca.mvp.data.models.User


class LoadUserState(private val userId: Long): AbstractEditUserPresenterState() {

    private var disposable: Disposable? = null

    override fun onApplied() {
        with(presenter) {
            view.showLoadIndicator()
            disposable = usersRepository.getUser().subscribe(
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
        }
    }

    override fun onLeft(finished: Boolean) {
        disposable?.dispose()
    }

    override fun createSavableInstance() = LoadUserState(userId)
}