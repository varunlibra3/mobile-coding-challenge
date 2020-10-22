package com.traderevcoding.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.traderevcoding.data.UnsplashPhoto
import com.traderevcoding.databinding.ListItemPhotoGridBinding
import com.traderevcoding.views.PhotoGridFragmentDirections

class PhotoGridAdapter :
    ListAdapter<UnsplashPhoto, PhotoGridAdapter.PhotoGridViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridViewHolder {
        return PhotoGridViewHolder(
            ListItemPhotoGridBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo, position)
        }
    }

    class PhotoGridViewHolder(
        private val binding: ListItemPhotoGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var pos = -1

        init {
            binding.setClickListener { view ->
                binding.photo?.let {
                    val direction = PhotoGridFragmentDirections.actionGridToDetailFragment(pos)
                    view.findNavController().navigate(direction)
                }
            }
        }

        fun bind(item: UnsplashPhoto, position: Int) {
            pos = position
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>() {
    override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem == newItem
    }
}
