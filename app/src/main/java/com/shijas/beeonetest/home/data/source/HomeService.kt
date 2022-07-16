package com.shijas.beeonetest.home.data.source

import com.shijas.beeonetest.home.data.dto.comment.CommentResponseDtoItem
import com.shijas.beeonetest.home.data.dto.post.PostDataResponseDtoItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("posts")
    suspend fun getPostDta() : ApiResponse<List<PostDataResponseDtoItem>>

    @GET("posts/{postId}/comments")
    suspend fun getComment(
        @Path("postId") postId: Int
    ) : ApiResponse<List<CommentResponseDtoItem>>
}