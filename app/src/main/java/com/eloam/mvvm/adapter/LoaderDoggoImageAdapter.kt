package com.eloam.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eloam.mvvm.MyApplication.Companion.context
import com.eloam.mvvm.R
import com.eloam.mvvm.ui.home.HomeViewModel
import org.lico.core.utils.ImageLoad

class LoaderDoggoImageAdapter :
    PagingDataAdapter<HomeViewModel.DoggoImageModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR =
            object : DiffUtil.ItemCallback<HomeViewModel.DoggoImageModel>() {
                override fun areItemsTheSame(
                    oldItem: HomeViewModel.DoggoImageModel,
                    newItem: HomeViewModel.DoggoImageModel
                ) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: HomeViewModel.DoggoImageModel,
                    newItem: HomeViewModel.DoggoImageModel
                ) =
                    oldItem.id == newItem.id
            }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? DoggoImageViewHolder)?.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoggoImageViewHolder.getInstance(parent)
    }

    /**
     * view holder class for doggo item
     */
    class DoggoImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            //get instance of the DoggoImageViewHolder
            fun getInstance(parent: ViewGroup): DoggoImageViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_doggo_image_view, parent, false)
                return DoggoImageViewHolder(view)
            }
        }

        var ivDoggoMain: ImageView = view.findViewById(R.id.ivDoggoMain)

        fun bind(item: HomeViewModel.DoggoImageModel?) {
            //loads image from network using coil extension function
            ImageLoad.loadImage(context!!, item?.url, ivDoggoMain)

        }

    }

}