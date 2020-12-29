package com.eloam.mvvm.ui.notifications

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eloam.mvvm.BR
import com.eloam.mvvm.R
import com.eloam.mvvm.adapter.LoaderDoggoImageAdapter
import com.eloam.mvvm.databinding.FragmentNotificationsBinding
import com.vikas.paging3.view.loader.adapter.LoaderStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.lico.core.base.BaseFragment

class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding, NotificationsViewModel, NotificationsViewModel>() {

    private val notificationsViewModel: NotificationsViewModel by viewModel()
    lateinit var adapter: LoaderDoggoImageAdapter
    lateinit var loaderStateAdapter: LoaderStateAdapter
    lateinit var rvDoggoRoom: RecyclerView

    override fun getViewModel(): NotificationsViewModel? {
        return notificationsViewModel
    }

    override fun getData(): NotificationsViewModel? {
        return notificationsViewModel
    }

    override fun getVariableId(): Int {
        return BR.notificationsModel

    }

    override fun layoutId(): Int {
        return R.layout.fragment_notifications
    }

    @ExperimentalPagingApi
    override fun initData() {
        initMembers()
        setUpViews()
        fetchDogImages()
    }

    private fun initMembers() {
        adapter = LoaderDoggoImageAdapter()
        loaderStateAdapter = LoaderStateAdapter { adapter.retry() }
    }

    private fun setUpViews() {
        rvDoggoRoom = binding!!.root.findViewById(R.id.rvDoggoRoom)
        rvDoggoRoom.layoutManager = GridLayoutManager(context, 6)
        rvDoggoRoom.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
    }



    @ExperimentalPagingApi
    private fun fetchDogImages() {
        lifecycleScope.launch {
            notificationsViewModel.fetchDoggoImages().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }
    //call this for live data based paging
    @ExperimentalPagingApi
    private fun fetchDoggoImagesLiveData() {
        notificationsViewModel.fetchDoggoImagesLiveData().observe(this, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    override fun getViewBinding(): FragmentNotificationsBinding {
        return binding
    }
}