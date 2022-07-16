package com.shijas.beeonetest.album.data.source

import com.shijas.beeonetest.album.data.dto.album.AlbumResponseDtoItem
import com.shijas.beeonetest.album.data.dto.photos.PhotosResponseDtoItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET("albums")
    suspend fun getAlbum() : ApiResponse<List<AlbumResponseDtoItem>>

    @GET("albums/{albumId}/photos")
    suspend fun getPhotos(
        @Path("albumId") albumId: Int
    ) : ApiResponse<List<PhotosResponseDtoItem>>



}