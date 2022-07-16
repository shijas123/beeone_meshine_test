package com.shijas.beeonetest.album.prsentation.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shijas.beeonetest.album.domain.model.photo.PhotosResponseModel
import com.shijas.beeonetest.databinding.ItemPhotoBinding

class ItemPhotoAdapter(): ListAdapter<PhotosResponseModel, RecyclerView.ViewHolder>(DiffCallback)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemPhotoViewHolder).bind(getItem(position))
    }

    private inner class ItemPhotoViewHolder(private val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(photosResponseModel: PhotosResponseModel){
            binding.apply {
                title.text =  photosResponseModel.title
                photoImg.load(photosResponseModel.url)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<PhotosResponseModel>() {
        override fun areItemsTheSame(
            oldItem: PhotosResponseModel,
            newItem: PhotosResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PhotosResponseModel,
            newItem: PhotosResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}