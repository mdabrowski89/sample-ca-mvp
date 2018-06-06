package pl.mobite.sample.ca.mvp.data.repositories


import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.mobite.sample.ca.mvp.data.local.room.UserDao
import pl.mobite.sample.ca.mvp.data.local.room.toUser
import pl.mobite.sample.ca.mvp.data.local.room.toUserEntity
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User

class UsersRepositoryImpl(private val userDao: UserDao) : UsersRepository {

    override fun getUsersPage(index: Int): Single<Page<User>> {
        return Single
                .fromCallable {
                    val count = userDao.count()
                    val pageNumber = Math.ceil(count.toDouble() / ITEMS_PER_PAGE).toInt()
                    val offset = index * ITEMS_PER_PAGE
                    if (offset > count) {
                        throw Exception()
                    }
                    val data = userDao.getRange(offset, ITEMS_PER_PAGE).map {
                        it.toUser()
                    }
                    Page(data, PageMetadata(index, pageNumber))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUsers(): Single<List<User>> {
        return Single
                .fromCallable {
                    userDao.getAll().map {
                        it.toUser()
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUser(userId: Long): Single<User> {
        return Single
                .fromCallable {
                    userDao.get(userId).toUser()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun createUser(user: User): Completable {
        return Completable
                .fromCallable {
                    userDao.insert(user.toUserEntity())
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateUser(user: User): Completable {
        return Completable
                .fromCallable {
                    userDao.update(user.toUserEntity())
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteUser(user: User): Completable {
        return Completable
                .fromCallable {
                    userDao.delete(user.toUserEntity())
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {

        const val ITEMS_PER_PAGE = 20
    }
}
