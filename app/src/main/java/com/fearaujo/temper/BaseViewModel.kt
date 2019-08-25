package com.fearaujo.temper

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

abstract class BaseViewModel: ViewModel(), KoinComponent {

    /**
     * This is a scope for all coroutines launched by [BaseViewModel]
     * that will be dispatched in Main Thread
     */
    protected val uiScope = CoroutineScope(Dispatchers.Main)

    /**
     * This is a scope for all coroutines launched by [BaseViewModel]
     * that will be dispatched in a Pool of Thread
     */
    protected val ioScope = CoroutineScope(Dispatchers.Default)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancel()
        ioScope.coroutineContext.cancel()
    }
}