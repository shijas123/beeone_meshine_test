package com.shijas.beeonetest.home.data.repository

import com.shijas.beeonetest.common.Constants
import com.shijas.beeonetest.common.Resource
import com.shijas.beeonetest.home.data.source.HomeService
import com.shijas.beeonetest.home.domain.model.comment.CommentResponseModel
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel
import com.shijas.beeonetest.home.domain.repository.HomeRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository{
    override fun getPosts(): Flow<Resource<List<PostResponseModel>>> = flow {
        emit(Resource.Loading)
        homeService.getPostDta().suspendOnSuccess {
            val postsResp = this.data
            val showPost = postsResp.map { it.toPostResponseModel() }
            emit(Resource.Success(showPost))
        }.suspendOnError {
            when (this.statusCode) {
                StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
            }
        }.suspendOnException {
            emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))

        }
    }

    override fun getComments(postId: Int): Flow<Resource<List<CommentResponseModel>>> = flow{
        emit(Resource.Loading)
        homeService.getComment(postId).suspendOnSuccess {

            val commentResp = this.data
            val showComment = commentResp.map { it.toCommentRespModel() }
            emit(Resource.Success(showComment))

        }.suspendOnError {
            when (this.statusCode) {
                StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
            }
        }.suspendOnException {
            emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))

        }
    }


}