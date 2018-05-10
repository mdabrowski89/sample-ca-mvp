package pl.mobite.sample.ca.mvp.di.modules

import dagger.Module
import dagger.Provides
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepository
import pl.mobite.sample.ca.mvp.data.repositories.UsersRepositoryStub
import javax.inject.Singleton


@Module class RepositoryModule {

    @Provides @Singleton fun provideUsersRepository() : UsersRepository {
        return UsersRepositoryStub()
    }
}