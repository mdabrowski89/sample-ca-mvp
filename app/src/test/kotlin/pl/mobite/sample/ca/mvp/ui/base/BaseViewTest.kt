package pl.mobite.sample.ca.mvp.ui.base

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import pl.mobite.sample.ca.mvp.utils.extensions.createPowerMock


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class BaseViewTest< V: View, P: Presenter<*>> {

    lateinit var presenterMock: P

    protected lateinit var view: V

    /**
     * This is not very clean way to do this but I was not able to find better solution.
     * The problem is that to create proper mocks the parameters must be `reified` and this is only possible in inline functions
     *
     * This method should be invoked in first line of `setUp()` fun in a child class
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified P1: Presenter<*>> init() {
        presenterMock = createPowerMock<P1>() as P
    }
}