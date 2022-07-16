package com.shijas.beeonetest.album.domain.repository

import com.shijas.beeonetest.album.domain.model.album.AlbumResponseModel
import com.shijas.beeonetest.album.domain.model.photo.PhotosResponseModel
import com.shijas.beeonetest.common.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbum() : Flow<Resource<List<AlbumResponseModel>>>

    fun getPhotos(photoId : Int) : Flow<Resource<List<PhotosResponseModel>>>

}