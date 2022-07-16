package com.shijas.beeonetest.album.data.repository

import com.shijas.beeonetest.album.data.source.AlbumService
import com.shijas.beeonetest.album.domain.model.album.AlbumResponseModel
import com.shijas.beeonetest.album.domain.model.photo.PhotosResponseModel
import com.shijas.beeonetest.album.domain.repository.AlbumRepository
import com.shijas.beeonetest.common.Constants
import com.shijas.beeonetest.common.Resource
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumService: AlbumService
): AlbumRepository {
    override fun getAlbum(): Flow<Resource<List<AlbumResponseModel>>>  = flow{
        emit(Resource.Loading)

        albumService.getAlbum().suspendOnSuccess {
            val albumResp = this.data
            val showAlbum =  albumResp.map { it.toAlbumResponseModel() }
            emit(Resource.Success(showAlbum))

        }.suspendOnError {
            when (this.statusCode) {
                StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
            }
        }.suspendOnException {
            emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))

        }

    }

    override fun getPhotos(photoId: Int): Flow<Resource<List<PhotosResponseModel>>> =  flow {

        emit(Resource.Loading)
        albumService.getPhotos(photoId).suspendOnSuccess {
            val photoResp = this.data
            val showPhotos = photoResp.map { it.toPhotoResponseModel() }
            emit(Resource.Success(showPhotos))
        }

    }


}