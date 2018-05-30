package pl.mobite.sample.ca.mvp.ui.custom.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.custom_recycler_view.view.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.utils.extensions.inflate
import pl.mobite.sample.ca.mvp.utils.extensions.visible

/**
 * RecyclerView inside FrameView with some additional features.
 * It can handle out of the box:
 * - default/custom loading view
 * - default/custom empty view
 * - click on empty view
 * - swipe to refresh
 */
abstract class CustomRecyclerView<in T, out A: RecyclerView.Adapter<*>> @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var onEmptyViewClickedListener: (() -> Unit)? = null
    var onSwipedToRefreshListener: (() -> Unit)? = null

    var emptyView: View? = null
    var loadingView: View? = null

    protected val adapter by lazy { createAdapter() }

    init {
        inflate(R.layout.custom_recycler_view, true)
        recyclerView.layoutManager = getLayoutManager()
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener { onSwipedToRefreshListener?.invoke() }
        getEmptyViewLayoutRes()?.let { layoutRes -> setEmptyView(layoutRes) }
        getLoadingViewLayoutRes()?.let { layoutRes -> setLoadingView(layoutRes) }
    }

    open fun setEmptyView(@LayoutRes layoutRes: Int) {
        emptyViewStub.layoutResource = layoutRes
        emptyView = emptyViewStub.inflate()
        emptyView?.setOnClickListener { onEmptyViewClickedListener?.invoke() }
    }

    open fun setLoadingView(@LayoutRes layoutRes: Int) {
        loadingViewStub.layoutResource = layoutRes
        loadingView = loadingViewStub.inflate()
    }

    open fun setItems(items: List<T>) {
        setItemsToAdapter(items)
        renderView(showRecyclerView = !items.isEmpty(), showEmptyView = items.isEmpty())
    }

    open fun setRefreshing() {
        renderView(showRecyclerView = true, showRefreshIndicator = true)
    }

    open fun setLoading() {
        renderView(showLoadingIndicator = true)
    }

    protected open fun renderView(
            showRecyclerView: Boolean = false,
            showEmptyView: Boolean = false,
            showLoadingIndicator: Boolean = false,
            showRefreshIndicator: Boolean = false) {
        swipeRefreshLayout.visible(showRecyclerView)
        emptyView?.visible(showEmptyView)
        loadingView?.visible(showLoadingIndicator)
        swipeRefreshLayout.isRefreshing = showRefreshIndicator
    }

    protected open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)

    fun getRecyclerView(): RecyclerView = recyclerView

    fun getSwipeRefreshLayout(): SwipeRefreshLayout = swipeRefreshLayout

    protected open fun getEmptyViewLayoutRes(): Int? = R.layout.custom_recycler_view_empty

    protected open fun getLoadingViewLayoutRes(): Int? = R.layout.custom_recycler_view_loading

    protected abstract fun setItemsToAdapter(items: List<T>)

    abstract fun createAdapter(): A
}

