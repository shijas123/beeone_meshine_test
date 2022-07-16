package com.shijas.beeonetest.album.prsentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shijas.beeonetest.album.domain.model.album.AlbumResponseModel
import com.shijas.beeonetest.databinding.ItemAlbumBinding
import com.shijas.beeonetest.databinding.ItemPostBinding
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel
import com.shijas.beeonetest.home.presentation.ItemPostAdapter

class ItemAlbumAdapter(private val listener: OnClick):  ListAdapter<AlbumResponseModel, RecyclerView.ViewHolder>(DiffCallback) {

    interface OnClick {
        fun onPhotoShowClick(albumResponseModel: AlbumResponseModel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return ItemAlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemAlbumViewHolder).bind(getItem(position))
    }

    private inner class ItemAlbumViewHolder(private val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(albumResponseModel: AlbumResponseModel){
            binding.apply {
                title.text = albumResponseModel.title

                root.setOnClickListener{
                    listener.onPhotoShowClick(albumResponseModel)
                }

            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<AlbumResponseModel>() {
        override fun areItemsTheSame(
            oldItem: AlbumResponseModel,
            newItem: AlbumResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AlbumResponseModel,
            newItem: AlbumResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}