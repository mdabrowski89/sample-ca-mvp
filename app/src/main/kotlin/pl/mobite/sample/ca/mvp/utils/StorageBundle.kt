package pl.mobite.sample.ca.mvp.utils

import android.os.Bundle
import java.io.Serializable


class StorageBundle(val bundle: Bundle?): Storage {

    override fun store(key: String, item: Serializable?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putSerializable(key, item) }
    }

    override fun store(key: String, item: String?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putString(key, item) }
    }

    override fun store(key: String, item: Boolean?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putBoolean(key, item) }
    }

    override fun store(key: String, item: Short?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putShort(key, item) }
    }

    override fun store(key: String, item: Byte?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putByte(key, item) }
    }

    override fun store(key: String, item: Char?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putChar(key, item) }
    }

    override fun store(key: String, item: Int?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putInt(key, item) }
    }

    override fun store(key: String, item: Long?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putLong(key, item) }
    }

    override fun store(key: String, item: Float?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putFloat(key, item) }
    }

    override fun store(key: String, item: Double?) {
        if (item == null) { bundle?.remove(key) } else { bundle?.putDouble(key, item) }
    }


    override fun restoreString(key: String): String? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getString(key) } else { null }
    }

    override fun restoreSerializable(key: String): Serializable? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getSerializable(key) } else { null }
    }

    override fun restoreBoolean(key: String): Boolean? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getBoolean(key) } else { null }
    }

    override fun restoreShort(key: String): Short? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getShort(key) } else { null }
    }

    override fun restoreByte(key: String): Byte? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getByte(key) } else { null }
    }

    override fun restoreChar(key: String): Char? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getChar(key) } else { null }
    }

    override fun restoreInt(key: String): Int? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getInt(key) } else { null }
    }

    override fun restoreLong(key: String): Long? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getLong(key) } else { null }
    }

    override fun restoreFloat(key: String): Float? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getFloat(key) } else { null }
    }

    override fun restoreDouble(key: String): Double? {
        return if (bundle?.containsKey(key) ?: false) { bundle?.getDouble(key) } else { null }
    }
}