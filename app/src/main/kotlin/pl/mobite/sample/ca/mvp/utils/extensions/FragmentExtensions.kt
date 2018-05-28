package pl.mobite.sample.ca.mvp.utils.extensions

import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment


inline fun <reified T: DialogFragment> Fragment.findDialog(dialogTag: String)
        = childFragmentManager.findFragmentByTag(dialogTag) as? T


inline fun <reified T: DialogFragment> Fragment.findDialog(dialogTag: String, newInstance: T)
        = childFragmentManager.findFragmentByTag(dialogTag) as? T ?: newInstance

@SuppressLint("CommitTransaction")
fun Fragment.showDialog(dialog: DialogFragment, dialogTag: String) {
    if (!dialog.isAdded) {
        childFragmentManager.beginTransaction().add(dialog, dialogTag).commitNowAllowingStateLoss()
    }
}

@SuppressLint("CommitTransaction")
fun Fragment.dismissDialog(dialog: DialogFragment) {
    if (dialog.isAdded) {
        childFragmentManager.beginTransaction().remove(dialog).commitNowAllowingStateLoss()
    }
}

fun Fragment.dismissDialog(dialogTag: String) {
    val dialog = childFragmentManager.findFragmentByTag(dialogTag) as? DialogFragment
    if (dialog != null) {
        dismissDialog(dialog)
    }
}

fun Fragment.startActivityAsNewTask(intent: Intent) {
    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}