package pl.mobite.sample.ca.mvp.data.repositories


import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.mobite.sample.ca.mvp.data.models.Page
import pl.mobite.sample.ca.mvp.data.models.PageMetadata
import pl.mobite.sample.ca.mvp.data.models.User
import java.util.*
import java.util.concurrent.TimeUnit

class UsersRepositoryStub : UsersRepository {

    // TODO: implement stable database stub, not randomly generated entries

    override fun getUsersPage(index: Int): Single<Page<User>> {
        return Single.create(SingleOnSubscribe<Page<User>> {
            e -> e.onSuccess(getFakeUsersPage(index))})
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getUsers(): Single<List<User>> {
        return Single.create(SingleOnSubscribe<List<User>> {
            e -> e.onSuccess(getFakeUsers(0)) })
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getFakeUsersPage(index: Int): Page<User> {
        return Page(getFakeUsers(index), PageMetadata(index, 5))
    }

    private fun getFakeUsers(page: Int): List<User> {
        val random = Random()
        val users = ArrayList<User>()
        if (page == PageMetadata.FIRST_PAGE_INDEX) {
            if (random.nextInt(5) == 0) {
                throw Exception()
            }
            if (random.nextInt(4) == 0) {
                return users
            }
        }
        for (i in 1..10) {
            users.add(User("User " + ((page * 10) + i), random.nextInt(50)))
        }
        return users
    }
}
