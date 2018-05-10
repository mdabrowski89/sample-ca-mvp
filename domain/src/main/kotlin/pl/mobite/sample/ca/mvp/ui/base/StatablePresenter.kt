package pl.mobite.sample.ca.mvp.ui.base

import pl.mobite.sample.ca.mvp.data.Storage


abstract class StatablePresenter<S: State<V>, V: View>(view: V): Presenter<V>(view) {

    private val SAVED_STATE_KEY = "SAVED_STATE"

    var state: S? = null

    @Suppress("UNCHECKED_CAST")
    final override fun onViewCreated(storage: Storage) {
        restoreData(storage)
        setNewState(storage.restoreSerializable(SAVED_STATE_KEY) as S? ?: createInitialState())
    }

    final override fun onViewSaved(storage: Storage) {
        state?.apply {
            storage.store(SAVED_STATE_KEY, createSavableInstance())
        }
        saveData(storage)
    }

    override fun onViewDestroyed(finished: Boolean) {
        state?.onLeft(finished)
        state = null
    }

    fun setNewState(newState: S) {
        state?.onLeft(false)
        state = newState
        state?.apply {
            init(this@StatablePresenter)
            onApplied()
        }
    }

    open fun saveData(storage: Storage) {}

    open fun restoreData(storage: Storage) {}

    protected abstract fun createInitialState(): S

}
