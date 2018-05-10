package pl.mobite.sample.ca.mvp.ui.custom.recyclerview.simple

import android.content.Context
import android.util.AttributeSet
import pl.mobite.sample.ca.mvp.ui.custom.recyclerview.CustomRecyclerView


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

