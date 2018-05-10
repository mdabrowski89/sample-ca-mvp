package pl.mobite.sample.ca.mvp.ui.custom

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.afollestad.materialdialogs.MaterialDialog


class ProgressDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialDialog.Builder(context!!).progress(true, 0).build()
    }

    companion object {

        fun newInstance(): ProgressDialog {
            return ProgressDialog()
        }
    }
}