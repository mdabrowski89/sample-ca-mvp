package pl.mobite.sample.ca.mvp.ui.custom.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class CustomViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: T, onItemClickedListener: ((T) -> Unit)?) {
        with(itemView) {
            bind(item)
            setOnClickListener { onItemClickedListener?.invoke(item) }
        }
    }

    abstract fun bind(item: T)

}
