package pl.mobite.sample.ca.mvp.utils.extensions

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment


inline fun <reified T: DialogFragment> AppCompatActivity.findDialog(dialogTag: String, newInstance: T): T {
    return supportFragmentManager.findFragmentByTag(dialogTag) as? T ?: newInstance
}

@SuppressLint("CommitTransaction")
fun AppCompatActivity.showDialog(dialog: DialogFragment, dialogTag: String) {
    if (!dialog.isAdded) {
        supportFragmentManager.beginTransaction().add(dialog, dialogTag).commitNowAllowingStateLoss()
    }
}

@SuppressLint("CommitTransaction")
fun AppCompatActivity.dismissDialog(dialog: DialogFragment) {
    if (dialog.isAdded) {
        supportFragmentManager.beginTransaction().remove(dialog).commitNowAllowingStateLoss()
    }
}

fun AppCompatActivity.dismissDialog(dialogTag: String) {
    val dialog = supportFragmentManager.findFragmentByTag(dialogTag) as? DialogFragment
    if (dialog != null) {
        dismissDialog(dialog)
    }
}

fun AppCompatActivity.startActivityAsNewTask(intent: Intent) {
    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}