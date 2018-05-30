package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.simple

import android.content.Context
import android.util.AttributeSet
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomRecyclerView

/**
 * Simple RecyclerView based on CustomRecyclerView.
 * It can handle out of the box:
 * - click on item
 * - default/custom loading view
 * - default/custom empty view
 * - click on empty view
 * - swipe to refresh
 *
 * You must provide subclass of SimpleRecyclerViewAdapter as its adapter
 */
abstract class SimpleRecyclerView<T, out A: SimpleRecyclerViewAdapter<T>> @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : CustomRecyclerView<T, A>(context, attrs, defStyleAttr) {

    var onItemClickedListener: ((T) -> Unit)? = null

    init {
        adapter.onItemClickedListener = { item -> onItemClickedListener?.invoke(item) }
    }

    override fun setItemsToAdapter(items: List<T>) {
        adapter.setItems(items)
    }
}

