package com.eloam.mvvm.ui.dashboard

import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.eloam.mvvm.BR
import com.eloam.mvvm.R
import com.eloam.mvvm.databinding.FragmentDashboardBinding
import com.eloam.mvvm.adapter.LoaderDoggoImageAdapter
import com.eloam.mvvm.ui.dashboard.adapter.DisAdapter
import com.vikas.paging3.view.loader.adapter.LoaderStateAdapter
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.lico.core.base.BaseFragment

class DashboardFragment :
    BaseFragment<FragmentDashboardBinding, DashboardViewModel, DashboardViewModel>() {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    lateinit var adapter: DisAdapter

    override fun getViewModel(): DashboardViewModel? {
        return dashboardViewModel
    }

    override fun getData(): DashboardViewModel? {
        return dashboardViewModel
    }

    override fun getVariableId(): Int {
        return BR.dashboardModel

    }

    override fun layoutId(): Int {
        return R.layout.fragment_dashboard
    }

    private fun initMembers() {
        adapter = DisAdapter()
        getViewBinding().rvDoggoRemote.layoutManager = GridLayoutManager(context, 5)
        getViewBinding().rvDoggoRemote.adapter = adapter


    }

    private fun getDoDogInfo() {
        lifecycleScope.launch {
            dashboardViewModel.fetchDogImagesInfo().collectLatest {

                adapter.submitData(it)

            }
        }

    }

    @ExperimentalPagingApi
    override fun initData() {
        initMembers()
        getDoDogInfo()

    }

    override fun getViewBinding(): FragmentDashboardBinding {
        return binding
    }

}