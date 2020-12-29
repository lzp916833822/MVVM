package com.eloam.mvvm.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eloam.mvvm.MyApplication
import com.eloam.mvvm.R
import com.eloam.mvvm.ui.home.HomeViewModel
import org.lico.core.utils.ImageLoad

class DisAdapter :
    PagingDataAdapter<HomeViewModel.DoggoImageModel, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<HomeViewModel.DoggoImageModel>() {
        override fun areItemsTheSame(
            oldItem: HomeViewModel.DoggoImageModel,
            newItem: HomeViewModel.DoggoImageModel
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: HomeViewModel.DoggoImageModel,
            newItem: HomeViewModel.DoggoImageModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DogImageViewHolder).bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DogImageViewHolder.getView(parent)
    }

    class DogImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun getView(parent: ViewGroup): DogImageViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_doggo_image_view, parent, false)
                return DogImageViewHolder(view)

            }

        }

        var ivDoggoMain: ImageView = view.findViewById(R.id.ivDoggoMain)

        fun bind(item: HomeViewModel.DoggoImageModel?) {
            //loads image from network using coil extension function
            ImageLoad.loadImage(MyApplication.context!!, item?.url, ivDoggoMain)

        }

    }
}