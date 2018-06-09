package pl.mobite.sample.ca.mvp.data.models

import java.io.Serializable


data class User (val id: Long?, val name: String, val age: Int): Serializable

data class UserFormData (val name: String?, val age: Int?): Serializable