package com.eloam.mvvm.ui.koin

import androidx.lifecycle.LiveData
import androidx.paging.*
import androidx.paging.rxjava2.observable
import com.eloam.mvvm.MyApplication
import com.eloam.mvvm.data.DogImagePagingSource
import com.eloam.mvvm.data.DoggoImagePagingSource
import com.eloam.mvvm.data.DoggoMediator
import com.eloam.mvvm.local.AppDatabase
import com.eloam.mvvm.ui.home.HomeViewModel
import com.eloam.mvvm.local.LocalInjector
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val apiService: ApiService = RetrofitClient.createRetrofit()
        .create(ApiService::class.java)
) {


    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
        fun getInstance() = DataRepository()

        //get doggo repository instance
    }


    fun letDoDogImageFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<HomeViewModel.DoggoImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { DogImagePagingSource(apiService) }).flow
    }

    /**
     * calling the paging source to give results from api calls
     * and returning the results in the form of flow [Flow<PagingData<DoggoImageModel>>]
     * since the [PagingDataAdapter] accepts the [PagingData] as the source in later stage
     */
    @ExperimentalPagingApi
    fun letDoggoImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<HomeViewModel.DoggoImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { DoggoImagePagingSource(apiService) }
        ).flow
    }

    //for rxjava users
    @ExperimentalPagingApi
    fun letDoggoImagesObservable(pagingConfig: PagingConfig = getDefaultPageConfig()): Observable<PagingData<HomeViewModel.DoggoImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { DoggoImagePagingSource(apiService) }
        ).observable
    }

    //for live data users
    @ExperimentalPagingApi
    fun letDoggoImagesLiveData(pagingConfig: PagingConfig = getDefaultPageConfig()): LiveData<PagingData<HomeViewModel.DoggoImageModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { DoggoImagePagingSource(apiService) }
        ).liveData
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }


    @ExperimentalPagingApi
    fun letDoggoImagesFlowDb(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<HomeViewModel.DoggoImageModel>> {
        val instance = AppDatabase.getInstance(MyApplication.context!!)

        val pagingSourceFactory = { instance.getDoggoImageModelDao().getAllDoggoModel() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = DoggoMediator(apiService, instance)
        ).flow
    }
}