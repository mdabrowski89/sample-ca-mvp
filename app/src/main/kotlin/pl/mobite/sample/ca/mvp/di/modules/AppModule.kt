package pl.mobite.sample.ca.mvp.di.modules

import dagger.Module
import dagger.Provides
import pl.mobite.sample.ca.mvp.MyApp
import javax.inject.Singleton


@Module class AppModule(private val app: MyApp) {

    @Provides @Singleton fun provideApp() = app
}