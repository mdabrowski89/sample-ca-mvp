package pl.mobite.sample.ca.mvp.ui.base

import pl.mobite.sample.ca.mvp.utils.Storage


abstract class Presenter<out V: View>(val view: V) {

    /**
     * Called when the view is created. Is called only once per presenter instance.
     * @param storage - can store values which should survive presenters recreation
     */
    open fun onViewCreated(storage: Storage) {}

    /**
     * Called when view goes to the foreground and user can interact with it.
     * Can be called multiple time per presenter instance, first call is followed by onViewCreated() call.
     */
    open fun onViewInForeground() {}

    /**
     * Called when view becomes active (view in on the foreground and now user can interact with it).
     * Can be called multiple time per presenter instance, each call is followed by onViewInForeground() call.
     */
    open fun onViewActivated() {}

    /**
     * Called when view becomes inactive (view is still on the foreground but user can't interact with it).
     * Can be called multiple time per presenter instance, each call is followed by onViewActivated() call.
     */
    open fun onViewInactivated() {}

    /**
     * Called when view goes to background.
     * Can be called multiple time per presenter instance, each call is followed by onViewInactivated() call,
     * last call is performed before onViewDestroyed(boolean).
     */
    open fun onViewInBackground() {}

    /**
     * Called when the view is destroyed. Is called only once per presenter instance.
     * @param finished - whether the view is completely finished and will not be recreated
     */
    open fun onViewDestroyed(finished: Boolean) {}

    /**
     * Called when the view is about to be destroyed and recreated.
     * Can be called multiple time per state instance, last call is performed before onViewDestroyed(boolean)
     * @param storage - can store values which should survive presenters recreation
     */
    open fun onViewSaved(storage: Storage) {}
}

interface View
