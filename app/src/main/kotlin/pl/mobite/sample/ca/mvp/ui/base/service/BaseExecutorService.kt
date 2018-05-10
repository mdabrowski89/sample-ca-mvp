package pl.mobite.sample.ca.mvp.ui.base.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import pl.mobite.sample.ca.mvp.ui.base.Executor
import pl.mobite.sample.ca.mvp.utils.StorageBundle

abstract class BaseExecutorService<T : Executor<*>> : Service() {

    lateinit var executor: T

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        executor.onStart(StorageBundle(intent?.extras))
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        executor.onDestroy()
        super.onDestroy()
    }
}