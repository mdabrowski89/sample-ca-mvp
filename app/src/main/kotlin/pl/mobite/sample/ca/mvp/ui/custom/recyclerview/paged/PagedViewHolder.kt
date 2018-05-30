package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.paged

import android.view.View
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomViewHolder


/**
 * ViewHolder with some additional features (based on CustomViewHolder).
 * It can handle out of the box:
 * - click on item
 * - click on placeholder
 *
 * It is used by PagedRecyclerView
 */
abstract class PagedViewHolder<T>(itemView: View): CustomViewHolder<T>(itemView) {

    fun bindPlaceholder(onItemClickedListener: (() -> Unit)?) {
        with(itemView) {
            bindPlaceholder()
            setOnClickListener { onItemClickedListener?.invoke() }
        }
    }

    abstract fun bindPlaceholder()

}