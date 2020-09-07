package com.amo.motherfinancetest.ui.base

import androidx.lifecycle.ViewModel
import com.amo.motherfinancetest.datasource.DataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(val dataSource: DataSource) : ViewModel(), CoroutineScope {

    protected val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}