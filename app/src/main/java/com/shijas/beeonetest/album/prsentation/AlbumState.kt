package com.shijas.beeonetest.album.prsentation

import com.shijas.beeonetest.album.domain.model.album.AlbumResponseModel
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel
import com.shijas.beeonetest.home.presentation.HomeState

sealed class AlbumState{
    data class Success(
        val showAlbum: List<AlbumResponseModel>,

        ) : AlbumState()


    data class Error(val message: String) : AlbumState()
    object Loading : AlbumState()
    object Empty : AlbumState()
}
