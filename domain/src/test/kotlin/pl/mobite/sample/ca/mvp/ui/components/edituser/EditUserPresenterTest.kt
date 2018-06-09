package pl.mobite.sample.ca.mvp.ui.components.edituser

import org.junit.Before
import org.junit.Test
import org.powermock.core.classloader.annotations.PrepareForTest
import pl.mobite.sample.ca.mvp.data.models.UserFormData
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.BasePresenterTest
import pl.mobite.sample.ca.mvp.ui.components.edituser.state.AbstractEditUserPresenterState
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.lazyPowerMock
import pl.mobite.sample.ca.mvp.utils.extensions.verify

@PrepareForTest(UserFormData::class)
class EditUserPresenterTest : BasePresenterTest<EditUserPresenter, EditUserView>() {

    private val usersRepositoryMock: UsersRepository by lazyPowerMock()
    private val stateMock: AbstractEditUserPresenterState by lazyPowerMock()

    @Before
    fun setUp() {
        init<EditUserView>()
        presenter = EditUserPresenter(viewMock, null, usersRepositoryMock)
        presenter.setNewState(stateMock)
    }

    @Test
    fun onUpdateUser() {
        val userFormData = createPowerMock<UserFormData>()
        presenter.onUpdateUser(userFormData)

        verify(stateMock).onUpdateUser(userFormData)
    }

    @Test
    fun onCreateUser() {
        val userFormData = createPowerMock<UserFormData>()
        presenter.onCreateUser(userFormData)

        verify(stateMock).onCreateUser(userFormData)
    }

    @Test
    fun onDeleteUser() {
        presenter.onDeleteUser()

        verify(stateMock).onDeleteUser()
    }

}