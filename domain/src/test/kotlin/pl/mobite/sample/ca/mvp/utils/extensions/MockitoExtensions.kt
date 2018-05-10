package pl.mobite.sample.ca.mvp.utils.extensions

import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.OngoingStubbing


/**
 * Simplification for `MockitoAnnotations.initMocks(testedClass)` method for kotlin
 */
fun initMocks(testedClass: Any) {
    MockitoAnnotations.initMocks(testedClass)
}

/**
 * Simplification for `Mockito.verify(createMock)` method for kotlin
 */
fun <T> verify(mock: T): T {
    return Mockito.verify(mock)
}

/**
 * Simplification for `Mockito.verifyZeroInteractions(createMock)` method for kotlin
 */
fun <T> verifyZeroInteractions(mock: T) {
    Mockito.verifyZeroInteractions(mock)
}

/**
 * Simplification for `Mockito.when(methodCall)` method for kotlin
 */
fun <T> whenever(methodCall: T): OngoingStubbing<T> {
    return Mockito.`when`(methodCall)
}

/**
 * Simplification for `Mockito.doNothing().when(createMock)` method for kotlin
 */
fun <T> doNothingWhen(mock: T): T {
    return Mockito.doNothing().`when`(mock)
}

/**
 * Simplification for `Mockito.doReturn().when(createMock)` method for kotlin
 */
fun <T> doReturnWhen(value: Any, mock: T): T {
    return Mockito.doReturn(value).`when`(mock)
}

/**
 * Fix for `Matchers.any(clazz)` method for kotlin
 */
inline fun <reified T> any(): T {
    return uninitialized(ArgumentMatchers.any(T::class.java))
}

fun <T> anyList(): List<T> {
    return uninitialized<List<T>>(ArgumentMatchers.anyList<T>())
}

/**
 * Fix for `Matchers.argThat(matcher)` method for kotlin
 */
fun <T> argThat(matcher: ArgumentMatcher<T>): T {
    return uninitialized(ArgumentMatchers.argThat(matcher))
}

/**
 * Workaround which allows to init non null variables
 */
@Suppress("UNCHECKED_CAST")
fun <T> uninitialized(value: T? = null) : T = value as T

/**
 * Create mock object of a specific class
 */
inline fun <reified T : Any> createMock(): T = Mockito.mock(T::class.java)

/**
 * Lazy delegate for creating mocks
 */
inline fun <reified T : Any> lazyMock(): Lazy<T> = lazy { createMock<T>() }




