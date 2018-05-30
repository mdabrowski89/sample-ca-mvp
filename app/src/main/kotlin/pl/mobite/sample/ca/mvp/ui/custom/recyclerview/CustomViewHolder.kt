package pl.mobite.sample.ca.mvp.ui.custom.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder with some additional features.
 * It can handle out of the box:
 * - click on item
 *
 * It is used by SimpleRecyclerViewAdapter, CustomPagedRecyclerViewAdapter
 * and it is parent class for PagedViewHolder which is used by PagedRecyclerViewAdapter
 */
abstract class CustomViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: T, onItemClickedListener: ((T) -> Unit)?) {
        with(itemView) {
            bind(item)
            setOnClickListener { onItemClickedListener?.invoke(item) }
        }
    }

    abstract fun bind(item: T)

}
