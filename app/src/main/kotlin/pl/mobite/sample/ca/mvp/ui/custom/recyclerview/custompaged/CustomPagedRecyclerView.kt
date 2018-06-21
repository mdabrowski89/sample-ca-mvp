package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.custompaged

import android.content.Context
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_recycler_view.view.*
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomRecyclerView

/**
 * RecyclerView with paging (based on CustomRecyclerView), it uses custom paging implementation.
 * It can handle out of the box:
 * - click on item
 * - new page loading when scroll to the end of current content
 * - default/custom loading view
 * - default/custom empty view
 * - click on empty view
 * - swipe to refresh
 *
 * It must provide subclass of CustomPagedRecyclerViewAdapter as its adapter
 */
abstract class CustomPagedRecyclerView<T, out A: CustomPagedRecyclerViewAdapter<T>> @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CustomRecyclerView<T, A>(context, attrs, defStyleAttr) {

    var onItemClickedListener: ((T) -> Unit)? = null
    var onScrolledToNextPageListener: (() -> Unit)? = null

    init {
        recyclerView.addOnScrollListener(OnScrollListener())
        adapter.onItemClickedListener = { item -> onItemClickedListener?.invoke(item) }
    }

    final override fun setItems(items: List<T>) {
        setItems(items, false)
    }

    final override fun setItemsToAdapter(items: List<T>) {
        throw RuntimeException("Should never be invoked, use setItems() method instead")
    }

    fun setItems(items: List<T>, hasMore: Boolean) {
        adapter.setItems(items, hasMore)
        renderView(showRecyclerView = !items.isEmpty(), showEmptyView = items.isEmpty())

        /**
         * Check if we do not need to load next page immediately
         */
        viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                loadNextPageIfNeeded()
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    override fun setRefreshing() {
        super.setRefreshing()
        disableNextPageLoading()
    }

    protected open fun disableNextPageLoading() {
        adapter.hasMoreItems.set(false)
        adapter.notifyDataSetChanged()
    }

    /**
     * Load next page if user scroll to the bottom
     */
    fun loadNextPageIfNeeded() {
        if (adapter.hasMoreItems.get()) {
            with(recyclerView) {
                val lastVisibleItemPosition = childCount + layoutManager!!.getPosition(getChildAt(0))
                if (lastVisibleItemPosition >= layoutManager!!.itemCount) {
                    onScrolledToNextPageListener?.invoke()
                }
            }
        }
    }

    inner class OnScrollListener: RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy <= 0) {
                return
            }
            loadNextPageIfNeeded()
        }
    }
}