package pl.mobite.sample.ca.mvp.utils.extensions

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlin.reflect.KFunction3
import kotlin.reflect.KProperty


fun SharedPreferences.Boolean(key: String, defValue: Boolean) = SharedPrefsDelegate(this, key, defValue, SharedPreferences::getBoolean, SharedPreferences.Editor::putBoolean)

fun SharedPreferences.Long(key: String, defValue: Long) = SharedPrefsDelegate(this, key, defValue, SharedPreferences::getLong, SharedPreferences.Editor::putLong)

fun SharedPreferences.Int(key: String, defValue: Int) = SharedPrefsDelegate(this, key, defValue, SharedPreferences::getInt, SharedPreferences.Editor::putInt)

fun SharedPreferences.String(key: String, defValue: String?) = SharedPrefsDelegate(this, key, defValue, SharedPreferences::getString, SharedPreferences.Editor::putString)

open class SharedPrefsDelegate<T>(private val prefs: SharedPreferences, private val key: String ,private val defValue: T,
                                  private val getFunction: KFunction3<SharedPreferences, String, T, T>,
                                  private val setFunction: KFunction3<SharedPreferences.Editor, String, T, SharedPreferences.Editor>) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = getFunction(prefs, key, defValue)

    @SuppressLint("CommitPrefEdits")
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setFunction(prefs.edit(), key, value).apply()
    }
}