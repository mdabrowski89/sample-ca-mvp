package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.simple

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomViewHolder


abstract class SimpleRecyclerViewAdapter<T>: RecyclerView.Adapter<CustomViewHolder<T>>() {

    var onItemClickedListener: ((T) -> Unit)? = null

    private var items = listOf<T>()

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T> {
        return getViewHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: CustomViewHolder<T>, position: Int) {
        holder.bind(items[position], onItemClickedListener)
    }

    final override fun getItemCount() = items.size

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T>

    fun setItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }
}