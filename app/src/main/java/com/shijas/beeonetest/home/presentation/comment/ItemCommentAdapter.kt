package com.shijas.beeonetest.home.presentation.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shijas.beeonetest.databinding.ItemCommentBinding
import com.shijas.beeonetest.home.domain.model.comment.CommentResponseModel


class ItemCommentAdapter() : ListAdapter<CommentResponseModel, RecyclerView.ViewHolder>(DiffCallback)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemCommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemCommentViewHolder).bind(getItem(position))
    }

    private inner class ItemCommentViewHolder(private val binding: ItemCommentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(commentResponseModel: CommentResponseModel){
            binding.apply {
                body1.text = commentResponseModel.body
                email.text =  commentResponseModel.email
                name.text = commentResponseModel.name
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<CommentResponseModel>() {
        override fun areItemsTheSame(
            oldItem: CommentResponseModel,
            newItem: CommentResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CommentResponseModel,
            newItem: CommentResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}