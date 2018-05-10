package pl.mobite.sample.ca.mvp.ui.base.activity


import android.os.Bundle
import pl.mobite.sample.ca.mvp.ui.base.Presenter
import pl.mobite.sample.ca.mvp.utils.StorageBundle

abstract class BasePresenterActivity<T: Presenter<*>> : BaseActivity() {

    lateinit var presenter: T

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onViewCreated(StorageBundle(savedInstanceState))
    }

    override fun onStart() {
        super.onStart()
        presenter.onViewInForeground()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewActivated()
    }

    override fun onPause() {
        super.onPause()
        presenter.onViewInactivated()
    }

    override fun onStop() {
        super.onStop()
        presenter.onViewInBackground()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed(isFinishing)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        presenter.onViewSaved(StorageBundle(outState))
    }
}
