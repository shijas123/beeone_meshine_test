package com.shijas.beeonetest.album.data.dto.photos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.shijas.beeonetest.album.domain.model.photo.PhotosResponseModel

@JsonClass(generateAdapter = true)
@Parcelize
data class PhotosResponseDtoItem(
    @Json(name = "albumId")
    val albumId: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "thumbnailUrl")
    val thumbnailUrl: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?
) : Parcelable {
    fun toPhotoResponseModel() : PhotosResponseModel {
        return  PhotosResponseModel(
            albumId = albumId ?: 0, id = id ?: 0, thumbnail = thumbnailUrl ?: "", title = title ?: "", url = url ?: ""
        )
    }
}