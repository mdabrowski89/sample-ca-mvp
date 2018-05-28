package pl.mobite.sample.ca.mvp.ui.custom.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CustomViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: T, onItemClickedListener: ((T) -> Unit)?) {
        with(itemView) {
            bind(item)
            setOnClickListener { onItemClickedListener?.invoke(item) }
        }
    }

    abstract fun bind(item: T)

}
