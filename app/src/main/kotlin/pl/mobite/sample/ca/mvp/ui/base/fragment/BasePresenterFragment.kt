package pl.mobite.sample.ca.mvp.ui.base.fragment


import android.os.Bundle
import pl.mobite.sample.ca.mvp.ui.base.Presenter
import pl.mobite.sample.ca.mvp.utils.StorageBundle

abstract class BasePresenterFragment<T: Presenter<*>> : BaseFragment() {

    lateinit var presenter: T

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        presenter.onViewDestroyed(activity!!.isFinishing)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onViewSaved(StorageBundle(outState))
    }
}
