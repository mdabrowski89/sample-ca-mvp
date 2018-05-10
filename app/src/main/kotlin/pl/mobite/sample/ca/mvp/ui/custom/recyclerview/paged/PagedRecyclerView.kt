package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import kotlinx.android.synthetic.main.custom_recycler_view.view.*
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomRecyclerView


abstract class PagedRecyclerView<T, out A: PagedRecyclerViewAdapter<T>> @JvmOverloads constructor(
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
        // do nothing - should be never invoked
    }

    fun setItems(items: List<T>, hasMore: Boolean) {
        adapter.setItems(items, hasMore)
        renderView(showRecyclerView = !items.isEmpty(), showEmptyView = items.isEmpty())
    }

    override fun setRefreshing() {
        super.setRefreshing()
        disableNextPageLoading()
    }

    protected open fun disableNextPageLoading() {
        adapter.hasMoreItems.set(false)
        adapter.notifyDataSetChanged()
    }

    inner class OnScrollListener: RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!adapter.hasMoreItems.get() || dy <= 0) {
                return
            }
            /**
             * Load next page if user scroll to the bottom
             */
            with(recyclerView) {
                val lastVisibleItemPosition = childCount + layoutManager.getPosition(getChildAt(0))
                if (lastVisibleItemPosition >= layoutManager.itemCount) {
                    onScrolledToNextPageListener?.invoke()
                }
            }
        }
    }
}