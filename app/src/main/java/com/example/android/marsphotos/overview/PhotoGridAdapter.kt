package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhotoData

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<MarsPhotoData>?
) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

class PhotoGridAdapter : ListAdapter<MarsPhotoData,
        PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    class MarsPhotoViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MarsPhoto: MarsPhotoData) {
            binding.photo = MarsPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhotoData>() {
        override fun areItemsTheSame(oldItem: MarsPhotoData, newItem: MarsPhotoData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhotoData, newItem: MarsPhotoData): Boolean {
            return oldItem.url == newItem.url
        }
    }
}