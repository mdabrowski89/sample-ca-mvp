package pl.mobite.sample.ca.mvp.utils.extensions

import android.content.Context
import android.telephony.TelephonyManager
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


fun Context.getTelephonyManager(): TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

fun Context.getDrawableCompat(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)