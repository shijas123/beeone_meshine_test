package com.shijas.beeonetest.album.prsentation.photos

import com.shijas.beeonetest.album.domain.model.photo.PhotosResponseModel

sealed class PhotosState{
    data class Success(
        val showPhotos: List<PhotosResponseModel>,

        ) : PhotosState()


    data class Error(val message: String) : PhotosState()
    object Loading : PhotosState()
    object Empty : PhotosState()
}
