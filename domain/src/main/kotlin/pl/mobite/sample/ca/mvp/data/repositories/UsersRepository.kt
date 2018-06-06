package pl.mobite.sample.ca.mvp.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.User

interface UsersRepository {

    fun getUsers(): Single<List<User>>

    fun getUsersPage(index: Int): Single<Page<User>>

    fun getUser(userId: Long): Single<User>

    fun createUser(user: User): Completable

    fun updateUser(user: User): Completable

    fun deleteUser(user: User): Completable
}