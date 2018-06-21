package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.disposables.Disposable
import pl.mobite.sample.ca.mvp.data.models.User


class CreateUserState(private val newUser: User): AbstractEditUserPresenterState() {

    private var disposable: Disposable? = null

    override fun onApplied() {
        with(presenter) {
            view.showCreateIndicator()
            disposable = usersRepository.createUser(newUser).subscribe(
                    /** onComplete */
                    {
                        view.onCreateSuccess()
                        setNewState(FinalState())
                    },
                    /** onError */
                    { _: Throwable? ->
                        view.showCreateError()
                        setNewState(NewUserFormState())
                    }
            )
        }
    }

    override fun onLeft(finished: Boolean) {
        disposable?.dispose()
    }

    override fun createSavableInstance() = NewUserFormState()
}