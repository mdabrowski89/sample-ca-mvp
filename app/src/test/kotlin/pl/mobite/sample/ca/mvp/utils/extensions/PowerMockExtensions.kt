package pl.mobite.sample.ca.mvp.utils.extensions

import org.powermock.api.mockito.PowerMockito
import org.powermock.reflect.Whitebox


/**
 * Create power mock object of a specific class
 */
inline fun <reified T : Any> createPowerMock(): T = PowerMockito.mock(T::class.java)

/**
 * Lazy delegate for creating power mocks
 */
inline fun <reified T : Any> lazyPowerMock(): Lazy<T> = lazy { createPowerMock<T>() }

/**
 * Set value for mock enum
 */
inline fun <reified T: Enum<T>>setUpEnumMock(enumObject: Any, enumValue: T) {
    Whitebox.setInternalState(enumObject, "name", enumValue.name)
    Whitebox.setInternalState(enumObject, "ordinal", enumValue.ordinal)
}




