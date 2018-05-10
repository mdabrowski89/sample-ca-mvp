package pl.mobite.sample.ca.mvp


import android.app.Application
import android.support.v7.app.AppCompatDelegate
import pl.mobite.sample.ca.mvp.di.AppComponent
import pl.mobite.sample.ca.mvp.di.DaggerAppComponent
import pl.mobite.sample.ca.mvp.di.modules.AppModule
import pl.mobite.sample.ca.mvp.di.modules.RepositoryModule

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .repositoryModule(RepositoryModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent.inject(this)

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
