package pl.mobite.sample.ca.mvp.data.model

import org.junit.Test
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.utils.extensions.assertTrue

class PageMetadataTest {

    @Test
    fun testInitialPage() {
        val testedPage = PageMetadata(PageMetadata.INITIAL_PAGE_INDEX, 5)
        assertTrue(testedPage.isInitialPage)
        assertTrue(testedPage.nextIndex == PageMetadata.FIRST_PAGE_INDEX)
    }

    @Test
    fun testFirstPage() {
        val testedPage = PageMetadata(PageMetadata.FIRST_PAGE_INDEX, 5)
        assertTrue(!testedPage.isInitialPage)
        assertTrue(testedPage.isFirst)
        assertTrue(!testedPage.isLast)
        assertTrue(testedPage.nextIndex == 1)
    }

    @Test
    fun testOnlyOnePage() {
        val testedPage = PageMetadata(PageMetadata.FIRST_PAGE_INDEX, 1)
        assertTrue(!testedPage.isInitialPage)
        assertTrue(testedPage.isFirst)
        assertTrue(testedPage.isLast)
    }

    @Test
    fun testMiddlePage() {
        val testedPage = PageMetadata(2, 6)
        assertTrue(!testedPage.isInitialPage)
        assertTrue(!testedPage.isFirst)
        assertTrue(!testedPage.isLast)
        assertTrue(testedPage.nextIndex == 3)
    }

    @Test
    fun testLastPage() {
        val testedPage = PageMetadata(5, 6)
        assertTrue(!testedPage.isInitialPage)
        assertTrue(!testedPage.isFirst)
        assertTrue(testedPage.isLast)
    }
}