package pl.mobite.sample.ca.mvp.di

import dagger.Component
import pl.mobite.sample.ca.mvp.di.modules.DaoModule
import pl.mobite.sample.ca.mvp.di.modules.RepositoryModule
import pl.mobite.sample.ca.mvp.ui.components.userslist.UsersListActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [DaoModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: UsersListActivity)
}