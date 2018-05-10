package pl.mobite.sample.ca.mvp.utils

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import pl.mobite.sample.ca.mvp.MyApp


class Baker(context: Context): Toast(context) {

    companion object {

        fun toast(message: String) {
            Toast.makeText(MyApp.instance, message, Toast.LENGTH_SHORT).show()
        }

        fun toast(messageRes: Int) {
            Toast.makeText(MyApp.instance, messageRes, Toast.LENGTH_SHORT).show()
        }

        fun snack(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT, show: Boolean = true): Snackbar {
            val snack = Snackbar.make(view, message, duration)
            if (show) {
                snack.show()
            }
            return snack
        }

        fun snack(view: View, messageRes: Int, duration: Int = Snackbar.LENGTH_SHORT, show: Boolean = true): Snackbar {
            val snack = Snackbar.make(view, messageRes, duration)
            if (show) {
                snack.show()
            }
            return snack
        }
    }
}