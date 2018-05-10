package pl.mobite.sample.ca.mvp.ui.base

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.modules.junit4.PowerMockRunner
import pl.mobite.sample.ca.mvp.utils.extensions.*

@RunWith(PowerMockRunner::class)
abstract class BasePresenterStateTest<S: State<V>, P: StatablePresenter<S, V>, V: View> {

    @Mock lateinit var viewMock: V
    @Mock lateinit var presenterMock: P

    protected var state: S = uninitialized()
    get() = presenterMock.state!!
    set(value) {
        field = value
        field.init(presenterMock)
        whenever(presenterMock.state).thenReturn(field)
    }

    /**
     * This is not very clean way to do this but I was not able to find better solution.
     * The problem is that to create proper mocks the parameters must be `reified` and this is only possible in inline functions
     *
     * This method should be invoked in first line of `setUp()` fun in a child class, even before `super.setUp()
     */
    @Suppress("UNCHECKED_CAST")
    protected inline fun <reified P1: StatablePresenter<S, V>, reified V1: View> init() {
        presenterMock = createPowerMock<P1>() as P
        viewMock = createMock<V1>() as V
    }

    @Suppress("UNCHECKED_CAST")
    @Before
    open fun setUp() {
        whenever(presenterMock.view).thenReturn(viewMock)
        whenever(presenterMock.setNewState(argThat(StateMatcher<S>()))).then { answer ->
            whenever(presenterMock.state).thenReturn(answer.arguments[0] as S)
            this
        }
    }

    protected inline fun <reified K> verifyStateIs() {
        assertTrue(state is K)
    }

    protected inline fun <reified K> verifyOnState(condition: (K) -> Boolean) {
        assertTrue(condition(state as K))
    }
}
