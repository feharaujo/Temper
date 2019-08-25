package com.fearaujo.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.fearaujo.data.di.REPOSITORY_TAG
import com.fearaujo.data.repository.remote.RemoteRepository
import com.fearaujo.model.Contractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RepositoryDataSource(
        private val remoteRepository: RemoteRepository,
        private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Contractor>() {

    private companion object {
        const val INITIAL_PAGE = 0
    }

    private val repoLiveData = MutableLiveData<RepositoryState>()
    private val supervisorJob = SupervisorJob()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Contractor>) {
        fetchContractors(INITIAL_PAGE) { contractors ->
            callback.onResult(contractors, null, increasePage(INITIAL_PAGE))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Contractor>) {
        val page = params.key
        fetchContractors(page) { contractors ->
            callback.onResult(contractors, increasePage(page))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Contractor>) {}

    /**
     * Remote call
     */
    private fun fetchContractors(page: Int, callback: (repositories: List<Contractor>) -> Unit) {
        repoLiveData.postValue(RepositoryState.Loading)

        scope.launch(getErrorHandler() + supervisorJob) {
            val contractors = remoteRepository.fetchContractorsByDate(page)
            repoLiveData.postValue(RepositoryState.Success)
            callback.invoke(contractors)
        }
    }

    private fun getErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e(REPOSITORY_TAG, "Repository error: $e")
        e.printStackTrace()
        repoLiveData.postValue(RepositoryState.OnError)
    }

    private fun increasePage(page: Int) = page.inc() + 1

    fun getRepoLiveData(): LiveData<RepositoryState> = repoLiveData

}