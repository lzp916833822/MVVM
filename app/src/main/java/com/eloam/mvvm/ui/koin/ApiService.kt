package com.eloam.mvvm.ui.koin

import com.eloam.mvvm.ui.home.HomeViewModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * @author: lzp
 * @create：2020/12/25
 * @describe：
 */
interface ApiService {

    companion object {
        const val IMAGES_SEARCH = "v1/images/search"
    }

    @GET(IMAGES_SEARCH)
    suspend fun getDoggoImages(@Query("page") page: Int, @Query("limit") size: Int): List<HomeViewModel.DoggoImageModel>
}