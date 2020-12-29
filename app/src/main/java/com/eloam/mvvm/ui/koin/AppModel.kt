package com.eloam.mvvm.ui.koin

import com.eloam.mvvm.ui.dashboard.DashboardViewModel
import com.eloam.mvvm.ui.home.HomeViewModel
import com.eloam.mvvm.ui.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { DashboardViewModel() }
    viewModel { NotificationsViewModel() }

}


private val repositoryModule = module {
    single { RetrofitClient.createRetrofit().create(ApiService::class.java) }
    single { DataRepository(get()) }

}


val appModule = listOf(viewModelModule, repositoryModule)


