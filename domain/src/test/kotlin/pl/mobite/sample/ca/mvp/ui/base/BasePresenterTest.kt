package pl.mobite.sample.ca.mvp.ui.base

import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import pl.mobite.sample.ca.mvp.utils.extensions.createMock

@RunWith(PowerMockRunner::class)
abstract class BasePresenterTest<P: Presenter<V>, V: View> {

    lateinit var viewMock: V

    protected lateinit var presenter: P

    /**
     * This is not very clean way to do this but I was not able to find better solution.
     * The problem is that to create proper mocks the parameters must be `reified` and this is only possible in inline functions
     *
     * This method should be invoked in first line of `setUp()` fun in a child class
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified V1: View> init() {
        viewMock = createMock<V1>() as V
    }
}