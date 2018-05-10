package pl.mobite.sample.ca.mvp.ui.custom.recyclerview

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.custom_recycler_view.view.*
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.utils.extensions.inflate
import pl.mobite.sample.ca.mvp.utils.extensions.visible


abstract class CustomRecyclerView<in T, out A: RecyclerView.Adapter<*>> @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var onEmptyViewClickedListener: (() -> Unit)? = null
    var onSwipedToRefreshListener: (() -> Unit)? = null

    var emptyView: View? = null

    protected val adapter by lazy { createAdapter() }

    init {
        inflate(R.layout.custom_recycler_view, true)
        recyclerView.layoutManager = getLayoutManager()
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener { onSwipedToRefreshListener?.invoke() }
        getEmptyViewLayoutRes()?.let { layoutRes -> setEmptyView(layoutRes) }
    }

    open fun setEmptyView(@LayoutRes layoutRes: Int) {
        emptyViewStub.layoutResource = layoutRes
        emptyView = emptyViewStub.inflate()
        emptyView?.setOnClickListener { onEmptyViewClickedListener?.invoke() }
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

    abstract fun getEmptyViewLayoutRes(): Int?

    protected open fun renderView(
            showRecyclerView: Boolean = false,
            showEmptyView: Boolean = false,
            showLoadingIndicator: Boolean = false,
            showRefreshIndicator: Boolean = false) {
        swipeRefreshLayout.visible(showRecyclerView)
        emptyView?.visible(showEmptyView)
        progressBar.visible(showLoadingIndicator)
        swipeRefreshLayout.isRefreshing = showRefreshIndicator
    }

    protected open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)

    fun getRecyclerView(): RecyclerView = recyclerView

    fun getSwipeRefreshLayout(): SwipeRefreshLayout = swipeRefreshLayout

    abstract fun createAdapter(): A

    protected abstract fun setItemsToAdapter(items: List<T>)
}

