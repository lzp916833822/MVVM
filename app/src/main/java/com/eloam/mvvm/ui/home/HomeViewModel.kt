package com.eloam.mvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import androidx.paging.rxjava2.cachedIn
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eloam.mvvm.ui.koin.DataRepository
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.lico.core.base.BaseViewModel

class HomeViewModel(
    private val repository: DataRepository = DataRepository.getInstance()
) : BaseViewModel() {

    @Entity
    data class DoggoImageModel(@PrimaryKey var id: String, val url: String)



    @ExperimentalPagingApi
    fun fetchDoggoImages(): Flow<PagingData<String>> {
        return repository.letDoggoImagesFlow()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)
    }

    //rxjava use case
    @ExperimentalPagingApi
    fun fetchDoggoImagesObservable(): Observable<PagingData<String>> {
        return repository.letDoggoImagesObservable()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)
    }

    //live data use case
    @ExperimentalPagingApi
    fun fetchDoggoImagesLiveData(): LiveData<PagingData<String>> {
        return repository.letDoggoImagesLiveData()
            .map { it.map { it.url } }
            .cachedIn(viewModelScope)
    }
}