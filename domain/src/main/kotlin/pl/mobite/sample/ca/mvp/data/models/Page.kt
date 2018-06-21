package pl.mobite.sample.ca.mvp.data.models

import java.io.Serializable


data class Page<out T>(val data: List<T>, val metadata: PageMetadata): Serializable

data class PageMetadata(val currentPageNumber: Int, private val allPagesNumber: Int): Serializable {

    private val lastPageNumber = FIRST_PAGE_NUMBER + allPagesNumber - 1

    val isInitialPage = currentPageNumber == INITIAL_PAGE_NUMBER

    val isFirst = currentPageNumber == FIRST_PAGE_NUMBER

    val isLast = currentPageNumber == lastPageNumber

    val nextPageNumber = currentPageNumber + 1

    companion object {
        const val INITIAL_PAGE_NUMBER: Int = -1
        const val FIRST_PAGE_NUMBER: Int = 0
    }
}
