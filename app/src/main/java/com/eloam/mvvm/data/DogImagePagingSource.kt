package com.eloam.mvvm.data

import androidx.paging.PagingSource
import com.eloam.mvvm.ui.home.HomeViewModel
import com.eloam.mvvm.ui.koin.ApiService
import com.eloam.mvvm.ui.koin.DataRepository
import com.eloam.mvvm.ui.koin.DataRepository.Companion.DEFAULT_PAGE_INDEX

class DogImagePagingSource(private val apiService: ApiService) :
    PagingSource<Int, HomeViewModel.DoggoImageModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeViewModel.DoggoImageModel> {
        val page = params.key ?: DataRepository.DEFAULT_PAGE_INDEX

        return try {
            val dogImages = apiService.getDoggoImages(page, params.loadSize)

            LoadResult.Page(
                dogImages,
                if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                if (dogImages.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }
}