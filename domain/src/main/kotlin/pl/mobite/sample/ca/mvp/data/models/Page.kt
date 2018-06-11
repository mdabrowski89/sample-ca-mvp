package pl.mobite.sample.ca.mvp.data.models

import java.io.Serializable


data class Page<out T>(val data: List<T>, val metadata: PageMetadata): Serializable

data class PageMetadata(val pageNumber: Int, private val allPages: Int): Serializable {

    private val lastPageIndex = FIRST_PAGE_INDEX + allPages - 1

    val isInitialPage = pageNumber == INITIAL_PAGE_INDEX

    val isFirst = pageNumber == FIRST_PAGE_INDEX

    val isLast = pageNumber == lastPageIndex

    val nextIndex = pageNumber + 1

    companion object {
        const val INITIAL_PAGE_INDEX: Int = -1
        const val FIRST_PAGE_INDEX: Int = 0
    }
}
