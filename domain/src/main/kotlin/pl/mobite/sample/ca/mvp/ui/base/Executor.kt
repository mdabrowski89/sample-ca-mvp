package pl.mobite.sample.ca.mvp.ui.base

import pl.mobite.sample.ca.mvp.utils.Storage

abstract class Executor<out C : ExecutorContext>(val context: C) {

    open fun onStart(storage: Storage) {}

    open fun onDestroy() {}
}

interface ExecutorContext