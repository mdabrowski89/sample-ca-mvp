package pl.mobite.sample.ca.mvp.data.repositories

import io.reactivex.Single
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.User

interface UsersRepository {

    // TODO: implement editing, removing and adding new items

    fun getUsers(): Single<List<User>>

    fun getUsersPage(index: Int): Single<Page<User>>
}