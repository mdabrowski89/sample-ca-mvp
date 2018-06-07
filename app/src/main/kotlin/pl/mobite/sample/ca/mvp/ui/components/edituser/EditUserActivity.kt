package pl.mobite.sample.ca.mvp.ui.components.edituser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_edit_user.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.data.models.User
import pl.mobite.sample.ca.mvp.data.models.UserFormData
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.ui.base.activity.BasePresenterActivity
import pl.mobite.sample.ca.mvp.utils.Baker
import pl.mobite.sample.ca.mvp.utils.extensions.appComponent
import pl.mobite.sample.ca.mvp.utils.extensions.visible
import javax.inject.Inject


class EditUserActivity: BasePresenterActivity<EditUserPresenter>(), EditUserView {

    @Inject lateinit var usersRepository: UsersRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_user)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        appComponent.inject(this)

        val userId = if (intent.hasExtra(EXTRA_USER_ID)) intent.getLongExtra(EXTRA_USER_ID, -1) else null

        title = getString(if (userId == null) R.string.edit_user_add_user_title else R.string.edit_user_edit_user_title)
        presenter = EditUserPresenter(this, userId, usersRepository)

        addUserButton.setOnClickListener {
            presenter.onCreateUser(getUserFormData())
        }

        saveUserButton.setOnClickListener {
            presenter.onUpdateUser(getUserFormData())
        }

        deleteUserButton.setOnClickListener {
            presenter.onDeleteUser()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(it.itemId) {
                android.R.id.home -> finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getUserFormData(): UserFormData {
        return UserFormData(userNameInput.text?.toString(), userAgeInput.text?.toString()?.toIntOrNull())
    }

    override fun showLoadIndicator() {
        renderView(showForm = false, showProgress = true)
    }

    override fun showUserForm(user: User) {
        renderView(user = user, showSaveButton = true, showDeleteButton = true)
    }

    override fun showNewUserForm() {
        renderView(showAddButton = true)
    }

    override fun showCreateIndicator() {
        renderView(showProgress = true)
    }

    override fun showUpdateIndicator() {
        renderView(showProgress = true)
    }

    override fun showDeleteIndicator() {
        renderView(showProgress = true)
    }

    override fun showCreateSuccess() {
        Baker.toast(R.string.edit_user_create_user_success_message)
    }

    override fun showUpdateSuccess() {
        Baker.toast(R.string.edit_user_update_user_success_message)
    }

    override fun showDeleteSuccess() {
        Baker.toast(R.string.edit_user_delete_user_success_message)
    }

    override fun showCreateError() {
        Baker.toast(R.string.edit_user_create_user_error_message)
    }

    override fun showUpdateError() {
        Baker.toast(R.string.edit_user_update_user_error_message)
    }

    override fun showDeleteError() {
        Baker.toast(R.string.edit_user_delete_user_error_message)
    }

    override fun showInvalidUserData() {
        Baker.toast(R.string.edit_user_invalid_user_data_message)
    }

    override fun showAppError() {
        Baker.toast(R.string.edit_user_app_error_message)
    }

    override fun close() {
        finish()
    }

    private fun renderView(
            showForm: Boolean = true,
            user: User? = null,
            showAddButton: Boolean = false,
            showSaveButton: Boolean = false,
            showDeleteButton: Boolean = false,
            showProgress: Boolean = false
    ) {
        userFormLayout.visible(showForm)
        user?.let {
            userNameInput.setText(user.name)
            userAgeInput.setText(user.age.toString())
        }
        addUserButton.visible(showAddButton)
        saveUserButton.visible(showSaveButton)
        deleteUserButton.visible(showDeleteButton)
        progressBar.visible(showProgress)
    }

    companion object {

        private const val EXTRA_USER_ID = "EXTRA_USER_ID"

        fun createIntent(context: Context, user: User) = Intent(context, EditUserActivity::class.java).apply {
            putExtra(EXTRA_USER_ID, user.id)
        }

        fun createIntene(context: Context) = Intent(context, EditUserActivity::class.java)
    }
}