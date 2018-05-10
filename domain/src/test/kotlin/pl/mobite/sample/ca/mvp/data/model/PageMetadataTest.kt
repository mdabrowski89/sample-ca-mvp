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
        assertTrue(testedPage.isValid)
        assertTrue(testedPage.nextIndex == 1)
    }

    @Test
    fun testOnlyOnePage() {
        val testedPage = PageMetadata(PageMetadata.FIRST_PAGE_INDEX, 1)
        assertTrue(!testedPage.isInitialPage)
        assertTrue(testedPage.isFirst)
        assertTrue(testedPage.isLast)
        assertTrue(testedPage.isValid)
    }

    @Test
    fun testMiddlePage() {
        val testedPage = PageMetadata(2, 6)
        assertTrue(!testedPage.isInitialPage)
        assertTrue(!testedPage.isFirst)
        assertTrue(!testedPage.isLast)
        assertTrue(testedPage.isValid)
        assertTrue(testedPage.nextIndex == 3)
    }

    @Test
    fun testLastPage() {
        val testedPage = PageMetadata(5, 6)
        assertTrue(!testedPage.isInitialPage)
        assertTrue(!testedPage.isFirst)
        assertTrue(testedPage.isLast)
        assertTrue(testedPage.isValid)
    }

    @Test
    fun testInvalidPages() {
        val testedPage1 = PageMetadata(-1, 6)
        val testedPage2 = PageMetadata(-1, -10)
        val testedPage3 = PageMetadata(-1, 0)
        val testedPage4 = PageMetadata(0, -2)
        val testedPage5 = PageMetadata(5, 2)
        val testedPage6 = PageMetadata(5, 0)
        val testedPage7 = PageMetadata(5, -2)
        assertTrue(!testedPage1.isValid)
        assertTrue(!testedPage2.isValid)
        assertTrue(!testedPage3.isValid)
        assertTrue(!testedPage4.isValid)
        assertTrue(!testedPage5.isValid)
        assertTrue(!testedPage6.isValid)
        assertTrue(!testedPage7.isValid)
    }
}