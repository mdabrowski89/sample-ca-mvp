package pl.mobite.sample.ca.mvp


import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import pl.mobite.sample.ca.mvp.data.local.room.AppDatabase
import pl.mobite.sample.ca.mvp.di.AppComponent
import pl.mobite.sample.ca.mvp.di.DaggerAppComponent
import pl.mobite.sample.ca.mvp.di.modules.DaoModule
import pl.mobite.sample.ca.mvp.di.modules.RepositoryModule



class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .daoModule(DaoModule(this))
                .repositoryModule(RepositoryModule())
                .build()
    }

    val db: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext,
                AppDatabase::class.java, "app-database").build()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        initCompatVectorResources()
    }

    private fun initCompatVectorResources() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    companion object {

        @JvmStatic lateinit var instance: MyApp
            private set
    }
}
