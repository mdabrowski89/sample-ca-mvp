package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mobite.sample.ca.mvp.R
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomViewHolder
import pl.mobite.sample.ca.mvp.utils.extensions.inflate
import java.util.concurrent.atomic.AtomicBoolean


abstract class PagedRecyclerViewAdapter<T>: RecyclerView.Adapter<CustomViewHolder<T>>() {

    var onItemClickedListener: ((T) -> Unit)? = null

    val hasMoreItems = AtomicBoolean(false)

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_PROGRESS = Int.MAX_VALUE

    private var items = listOf<T>()

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T> {
        return when(viewType) {
            VIEW_TYPE_PROGRESS -> object: ProgressViewHolder<T>(parent.inflate(R.layout.custom_item_progress)) {}
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
            = if (!hasMoreItems.get() || position <= items.lastIndex) VIEW_TYPE_ITEM else VIEW_TYPE_PROGRESS

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T>

    fun setItems(items: List<T>, hasMore: Boolean) {
        this.items = items
        this.hasMoreItems.set(hasMore)
        notifyDataSetChanged()
    }

    abstract class ProgressViewHolder<T>(itemView: android.view.View) : CustomViewHolder<T>(itemView) {

        override fun bind(item: T) {
            // do nothing
        }
    }
}