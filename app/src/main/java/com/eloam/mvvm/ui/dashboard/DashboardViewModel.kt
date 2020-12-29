package com.eloam.mvvm.ui.dashboard

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.eloam.mvvm.ui.home.HomeViewModel
import com.eloam.mvvm.ui.koin.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.lico.core.base.BaseViewModel

class DashboardViewModel(val repository: DataRepository = DataRepository.getInstance()) :
    BaseViewModel() {

    @ExperimentalPagingApi
    fun fetchDogImages(): Flow<PagingData<String>> {
        return repository.letDoggoImagesFlow().map { it.map { it.url } }.cachedIn(viewModelScope)
    }


    fun fetchDogImagesInfo(): Flow<PagingData<HomeViewModel.DoggoImageModel>> {

        return repository.letDoDogImageFlow().cachedIn(viewModelScope)
    }
}