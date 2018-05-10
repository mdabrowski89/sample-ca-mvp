package pl.mobite.sample.ca.mvp.di

import dagger.Component
import pl.mobite.sample.ca.mvp.MyApp
import pl.mobite.sample.ca.mvp.di.modules.AppModule
import pl.mobite.sample.ca.mvp.di.modules.RepositoryModule
import pl.mobite.sample.ca.mvp.ui.components.userslist.UsersListActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class), (RepositoryModule::class)])
interface AppComponent {

    fun inject(app: MyApp)

    fun inject(activity: UsersListActivity)
}