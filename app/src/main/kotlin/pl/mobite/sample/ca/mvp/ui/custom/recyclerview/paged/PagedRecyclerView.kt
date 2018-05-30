package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged

import android.content.Context
import android.util.AttributeSet
import androidx.paging.PagedList
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomRecyclerView

/**
 * RecyclerView with paging (based on CustomRecyclerView), it uses Paging lib from Android Architecture Components.
 * It can handle out of the box:
 * - click on item
 * - default/custom loading view
 * - default/custom empty view
 * - click on empty view
 * - swipe to refresh
 *
 * It must provide subclass of PagedRecyclerViewAdapter as its adapter
 */
abstract class PagedRecyclerView<T, out A: PagedRecyclerViewAdapter<T>> @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CustomRecyclerView<T, A>(context, attrs, defStyleAttr) {

    var onItemClickedListener: ((T) -> Unit)? = null

    init {
        adapter.onItemClickedListener = { item -> onItemClickedListener?.invoke(item) }
    }

    override fun setItemsToAdapter(items: List<T>) {
        adapter.submitList(items as PagedList<T>)
    }
}

