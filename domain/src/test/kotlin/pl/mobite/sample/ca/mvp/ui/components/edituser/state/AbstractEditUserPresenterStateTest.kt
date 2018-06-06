package pl.mobite.sample.ca.mvp.ui.components.edituser.state

import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.powermock.core.classloader.annotations.PrepareForTest
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.models.UserFormData
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterStateTest
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserPresenter
import pl.mobite.sample.ca.mvp.ui.components.edituser.EditUserView


@PrepareForTest(EditUserPresenter::class, User::class, UserFormData::class, Completable::class, Single::class)
abstract class AbstractEditUserPresenterStateTest : BasePresenterStateTest<AbstractEditUserPresenterState, EditUserPresenter, EditUserView>() {

    protected val user = User(1, "John Doe", 29)
    protected val userFormData = UserFormData("John Doe", 29)

    @Before
    override fun setUp() {
        init<EditUserPresenter, EditUserView>()
        super.setUp()
    }
}