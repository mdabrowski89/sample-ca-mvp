package pl.mobite.sample.ca.mvp.data.models

import java.io.Serializable


data class Page<out T>(val data: List<T>, val metadata: PageMetadata): Serializable

data class PageMetadata(val index: Int, val numbers: Int): Serializable {

    private val lastPageIndex = FIRST_PAGE_INDEX + numbers - 1

    val isInitialPage = index == INITIAL_PAGE_INDEX

    val isFirst = index == FIRST_PAGE_INDEX

    val isLast = index == lastPageIndex

    val isValid = index in FIRST_PAGE_INDEX.. lastPageIndex

    val nextIndex = index + 1

    companion object {
        const val INITIAL_PAGE_INDEX: Int = -1
        const val FIRST_PAGE_INDEX: Int = 0
    }
}
