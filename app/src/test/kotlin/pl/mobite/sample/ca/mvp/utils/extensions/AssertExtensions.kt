package pl.mobite.sample.ca.mvp.utils.extensions

import org.junit.Assert


/**
 * Verify if two lists have the same content
 */
fun verifyContentEquals(list1: List<*>, list2: List<*>) {
    verifyContentEquals(list1.toTypedArray(), list2.toTypedArray())
}

/**
 * Verify if two lists have the same content
 */
fun verifyContentEquals(array1: Array<*>, array2: Array<*>) {
    assertTrue(array1 contentDeepEquals array2)
}

/**
 * Simplification for `Assert.assertTrue(condition)` method for kotlin
 */
fun assertTrue(condition: Boolean) {
    Assert.assertTrue(condition)
}

/**
 * Simplification for `Assert.assertNotNull(object)` method for kotlin
 */
fun assertNotNull(component: Any?) {
    Assert.assertNotNull(component)
}





