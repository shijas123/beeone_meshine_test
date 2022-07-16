package com.shijas.beeonetest.home.domain.repository

import com.shijas.beeonetest.common.Resource
import com.shijas.beeonetest.home.domain.model.comment.CommentResponseModel
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getPosts() : Flow<Resource<List<PostResponseModel>>>

    fun getComments(postId : Int): Flow<Resource<List<CommentResponseModel>>>
}