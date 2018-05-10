package pl.mobite.sample.ca.mvp.ui.base

import org.mockito.ArgumentMatcher

class StateMatcher<S: State<*>>: ArgumentMatcher<S> {

    override fun matches(argument: S): Boolean {
        return true
    }
}
