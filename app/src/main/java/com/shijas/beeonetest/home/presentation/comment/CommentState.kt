package com.shijas.beeonetest.home.presentation.comment

import com.shijas.beeonetest.home.domain.model.comment.CommentResponseModel
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel
import com.shijas.beeonetest.home.presentation.HomeState

sealed class CommentState{
    data class Success(
        val showComment: List<CommentResponseModel>,

        ) : CommentState()


    data class Error(val message: String) : CommentState()
    object Loading : CommentState()
    object Empty : CommentState()
}
