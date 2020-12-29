package com.eloam.mvvm.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.eloam.mvvm.ui.home.HomeViewModel
import com.eloam.mvvm.ui.koin.ApiService
import com.eloam.mvvm.ui.koin.DataRepository.Companion.DEFAULT_PAGE_INDEX

import retrofit2.HttpException
import java.io.IOException

/**
 * provides the data source for paging lib from api calls
 */
@ExperimentalPagingApi
class DoggoImagePagingSource(val doggoApiService: ApiService) :
    PagingSource<Int, HomeViewModel.DoggoImageModel>() {

    /**
     * calls api if there is any error getting results then return the [LoadResult.Error]
     * for successful response return the results using [LoadResult.Page] for some reason if the results
     * are empty from service like in case of no more data from api then we can pass [null] to
     * send signal that source has reached the end of list
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeViewModel.DoggoImageModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = doggoApiService.getDoggoImages(page, params.loadSize)
            LoadResult.Page(
                response,  if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}