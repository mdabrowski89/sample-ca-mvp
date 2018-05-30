package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * PagedListAdapter with paging (Paging lib from Android Architecture Components).
 * It can handle out of the box:
 * - click on item
 * - click on placeholder
 *
 * You must provide subclass of PagedViewHolder as its ViewHolder.
 * It is used by PagedRecyclerViewAdapter.
 */
abstract class PagedRecyclerViewAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>)
    : PagedListAdapter<T, PagedViewHolder<T>>(diffCallback) {

    var onItemClickedListener: ((T) -> Unit)? = null
    var onPlaceholderClickedListener: (() -> Unit)? = null

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagedViewHolder<T> {
        return getViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: PagedViewHolder<T>, position: Int) {
        val item: T? = getItem(position)
        if (item == null) {
            holder.bindPlaceholder(onPlaceholderClickedListener)
        } else {
            holder.bind(item, onItemClickedListener)
        }
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): PagedViewHolder<T>
}