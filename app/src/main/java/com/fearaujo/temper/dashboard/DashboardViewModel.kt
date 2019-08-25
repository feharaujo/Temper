package com.fearaujo.temper.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fearaujo.data.repository.RepositoryDataSource
import com.fearaujo.data.repository.RepositorySourceFactory
import com.fearaujo.data.repository.RepositoryState
import com.fearaujo.model.Contractor
import com.fearaujo.temper.BaseViewModel
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

abstract class DashboardViewModel : BaseViewModel() {

    abstract fun subscribeRepositoryList(): LiveData<PagedList<Contractor>>

    abstract fun subscribeStatus(): LiveData<RepositoryState>

}

class DashboardViewModelImpl(pagedListConfig: PagedList.Config) : DashboardViewModel() {

    private val factory: RepositorySourceFactory by inject { parametersOf(ioScope) }

    private val listLiveData: LiveData<PagedList<Contractor>> =
            LivePagedListBuilder(factory, pagedListConfig).build()

    private val progressStatusLiveData = Transformations.switchMap(
            factory.getRepoLiveData(),
            RepositoryDataSource::getRepoLiveData
    )

    override fun subscribeRepositoryList(): LiveData<PagedList<Contractor>> {
        return listLiveData
    }

    override fun subscribeStatus(): LiveData<RepositoryState> {
        return progressStatusLiveData
    }

}
