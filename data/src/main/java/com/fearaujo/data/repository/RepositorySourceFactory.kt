package com.fearaujo.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fearaujo.data.repository.remote.RemoteRepository
import com.fearaujo.model.Contractor
import kotlinx.coroutines.CoroutineScope

class RepositorySourceFactory(
    private val remoteRepository: RemoteRepository,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Contractor>() {

    private val repoLiveData = MutableLiveData<RepositoryDataSource>()

    override fun create(): DataSource<Int, Contractor> {
        val dataSource = RepositoryDataSource(remoteRepository, scope)
        repoLiveData.postValue(dataSource)
        return dataSource
    }

    fun getRepoLiveData(): LiveData<RepositoryDataSource> = repoLiveData
}