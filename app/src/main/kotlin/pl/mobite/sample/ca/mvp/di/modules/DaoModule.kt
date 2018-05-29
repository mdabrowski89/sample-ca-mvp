package pl.mobite.sample.ca.mvp.di.modules

import dagger.Module
import dagger.Provides
import pl.mobite.sample.ca.mvp.MyApp
import pl.mobite.sample.ca.mvp.data.local.room.UserDao
import javax.inject.Singleton


@Module class DaoModule(val app: MyApp) {

    @Provides @Singleton fun provideUsersDao() : UserDao {
        return app.db.userDao()
    }
}