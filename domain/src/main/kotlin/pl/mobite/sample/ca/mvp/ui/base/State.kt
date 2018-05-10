package pl.mobite.sample.ca.mvp.ui.base

import java.io.Serializable

interface State<V: View>: Serializable {

    /**
     * Called to pass presenter reference to State object
     */
    fun init(presenter: StatablePresenter<*, V>)

    /**
     * Called when presenter enters this state instance. It is called only once per state instance.
     * When the call happen the presenters view is created and ready for receiving events
     */
    fun onApplied() {}

    /**
     * Called when presenter this state instance. It is called only once per state instance.
     * When the call happen the presenters view should not receive any events from this state instance
     * @param finished - whether the presenters is completely finished and will not be recreated
     */
    fun onLeft(finished: Boolean) {}

    /**
     * Called when there is a need to create savable state instance (presenter may be destroyed and recreated).
     * Can be called multiple time per state instance, last call is performed before onLeft(boolean).
     */
    fun createSavableInstance() : Serializable
}

