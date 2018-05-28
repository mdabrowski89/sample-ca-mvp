package pl.mobite.sample.ca.mvp.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat


fun ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun SwitchCompat.setCheckedWithoutAnimation(isChecked: Boolean) {
    val wasVisible = visibility == View.VISIBLE
    if (wasVisible) visibility = View.GONE
    this.isChecked = isChecked
    if (wasVisible) visibility = View.VISIBLE
}