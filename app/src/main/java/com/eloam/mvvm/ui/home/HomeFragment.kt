package com.eloam.mvvm.ui.home

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eloam.mvvm.BR
import com.eloam.mvvm.R
import com.eloam.mvvm.databinding.FragmentHomeBinding
import com.eloam.mvvm.adapter.LoaderDoggoImageAdapter
import com.vikas.paging3.view.loader.adapter.LoaderStateAdapter
import com.vikas.paging3.view.remote.adapter.RemoteDoggoImageAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.lico.core.base.BaseFragment

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModel()
    lateinit var rvDoggoRemote: RecyclerView
    lateinit var adapter: RemoteDoggoImageAdapter

    override fun getViewModel(): HomeViewModel? {
        return homeViewModel
    }

    override fun getData(): HomeViewModel? {
        return homeViewModel
    }

    override fun getVariableId(): Int {
        return BR.homeModel
    }

    override fun layoutId(): Int {
        return R.layout.fragment_home
    }

    private fun initMembers() {
        adapter = RemoteDoggoImageAdapter()

    }

    private fun setUpViews() {

        rvDoggoRemote = getViewBinding().rvDoggoRemote
        rvDoggoRemote.layoutManager = GridLayoutManager(context, 5)
        rvDoggoRemote.adapter = adapter
    }

    @ExperimentalPagingApi
    override fun initData() {
        initMembers()
        setUpViews()
        fetchDoggoImagesLiveData()
    }

    @ExperimentalPagingApi
    private fun fetchDoggoImages() {
        lifecycleScope.launch {
            homeViewModel.fetchDoggoImages().collectLatest {
                adapter.submitData(it)
            }
        }
    }


    //call this for rxjava observable based paging
    @ExperimentalPagingApi
    @SuppressLint("CheckResult")
    private fun fetchDoggoImagesObservable() {
        homeViewModel.fetchDoggoImagesObservable().subscribe {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

    //call this for live data based paging
    @ExperimentalPagingApi
    private fun fetchDoggoImagesLiveData() {
        homeViewModel.fetchDoggoImagesLiveData().observe(this, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    override fun getViewBinding(): FragmentHomeBinding{
        return binding
    }
}