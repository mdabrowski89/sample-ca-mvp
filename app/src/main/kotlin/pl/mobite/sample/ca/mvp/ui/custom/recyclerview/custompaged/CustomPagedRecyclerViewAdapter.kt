package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.custompaged

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomViewHolder
import pl.mobite.sample.ca.mvp.utils.extensions.inflate
import java.util.concurrent.atomic.AtomicBoolean

/**
 * RecyclerViewAdapter with custom paging implementation.
 * It can handle out of the box:
 * - click on item
 * - default/custom new page loading indicator
 *
 * You must provide subclass of CustomViewHolder as its ViewHolder.
 * It is used by CustomPagedRecyclerView.
 */
abstract class CustomPagedRecyclerViewAdapter<T>: RecyclerView.Adapter<CustomViewHolder<T>>() {

    var onItemClickedListener: ((T) -> Unit)? = null

    val hasMoreItems = AtomicBoolean(false)

    protected var items = listOf<T>()

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T> {
        return when(viewType) {
            VIEW_TYPE_LOADING -> object: LoadingViewHolder<T>(parent.inflate(getLoadingViewLayoutRes())) {}
            else -> getViewHolder(parent, viewType)
        }
    }

    final override fun onBindViewHolder(holder: CustomViewHolder<T>, position: Int) {
        if(holder.itemViewType == VIEW_TYPE_ITEM) {
            holder.bind(items[position], onItemClickedListener)
        }
    }

    final override fun getItemCount()
            = if (hasMoreItems.get()) items.size + 1 else items.size

    override fun getItemViewType(position: Int): Int
            = if (!hasMoreItems.get() || position <= items.lastIndex) VIEW_TYPE_ITEM else VIEW_TYPE_LOADING

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T>

    open fun setItems(items: List<T>, hasMore: Boolean) {
        this.items = items
        this.hasMoreItems.set(hasMore)
        notifyDataSetChanged()
    }

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = Int.MAX_VALUE
    }

    open fun getLoadingViewLayoutRes(): Int = R.layout.custom_item_loading

    abstract class LoadingViewHolder<T>(itemView: View) : CustomViewHolder<T>(itemView) {

        override fun bind(item: T) {
            // do nothing
        }
    }
}