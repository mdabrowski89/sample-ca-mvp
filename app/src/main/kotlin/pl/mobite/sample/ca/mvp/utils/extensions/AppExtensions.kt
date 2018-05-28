package pl.mobite.sample.ca.mvp.utils.extensions

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.fragment.app.Fragment
import pl.mobite.sample.ca.mvp.MyApp
import pl.mobite.sample.ca.mvp.di.AppComponent

fun Application.startActivityAsNewTask(intent: Intent) {
    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

val Activity.appComponent: AppComponent
    get() = (application as MyApp).appComponent

val Fragment.appComponent: AppComponent
    get() = activity!!.appComponent