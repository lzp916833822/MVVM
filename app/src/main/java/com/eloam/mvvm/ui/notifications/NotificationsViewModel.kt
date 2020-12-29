package com.eloam.mvvm.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eloam.mvvm.ui.home.HomeViewModel
import com.eloam.mvvm.ui.koin.DataRepository
import kotlinx.coroutines.flow.Flow
import org.lico.core.base.BaseViewModel

class NotificationsViewModel(val repository: DataRepository = DataRepository.getInstance()) : BaseViewModel() {

    @ExperimentalPagingApi
    fun fetchDoggoImages(): Flow<PagingData<HomeViewModel.DoggoImageModel>> {
        return repository.letDoggoImagesFlowDb().cachedIn(viewModelScope)
    }


    //live data use case
    @ExperimentalPagingApi
    fun fetchDoggoImagesLiveData(): LiveData<PagingData<HomeViewModel.DoggoImageModel>> {
        return repository.letDoggoImagesLiveData()
            .cachedIn(viewModelScope)
    }

}