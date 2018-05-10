package pl.mobite.sample.ca.mvp.data

import java.io.Serializable


interface Storage {

    fun store(key: String, item: Serializable?)

    fun store(key: String, item: String?)

    fun store(key: String, item: Boolean?)

    fun store(key: String, item: Short?)

    fun store(key: String, item: Byte?)

    fun store(key: String, item: Char?)

    fun store(key: String, item: Int?)

    fun store(key: String, item: Long?)

    fun store(key: String, item: Float?)

    fun store(key: String, item: Double?)

    fun restoreSerializable(key: String) : Serializable?

    fun restoreString(key: String) : String?

    fun restoreBoolean(key: String) : Boolean?

    fun restoreShort(key: String) : Short?

    fun restoreByte(key: String) : Byte?

    fun restoreChar(key: String) : Char?

    fun restoreInt(key: String) : Int?

    fun restoreLong(key: String) : Long?

    fun restoreFloat(key: String) : Float?

    fun restoreDouble(key: String) : Double?

}