package com.shijas.beeonetest.home.presentation

import com.shijas.beeonetest.home.domain.model.post.PostResponseModel

sealed class HomeState {
    data class Success(
        val showPost: List<PostResponseModel>,

        ) : HomeState()


    data class Error(val message: String) : HomeState()
    object Loading : HomeState()
    object Empty : HomeState()
}
