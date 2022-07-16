package com.shijas.beeonetest.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shijas.beeonetest.databinding.ItemPostBinding
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel

class ItemPostAdapter(private val listener: OnClick): ListAdapter<PostResponseModel, RecyclerView.ViewHolder>(DiffCallback) {

    interface OnClick {
        fun onPostShowClick(postResponseModel: PostResponseModel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemPostViewHolder).bind(getItem(position))
    }

    private inner class ItemPostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(postResponseModel: PostResponseModel){
            binding.apply {
                title.text = postResponseModel.title
                body.text = postResponseModel.body

                root.setOnClickListener{
                    listener.onPostShowClick(postResponseModel)
                }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<PostResponseModel>() {
        override fun areItemsTheSame(
            oldItem: PostResponseModel,
            newItem: PostResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostResponseModel,
            newItem: PostResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }


}