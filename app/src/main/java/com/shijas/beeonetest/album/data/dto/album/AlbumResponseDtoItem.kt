package com.shijas.beeonetest.album.data.dto.album


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.shijas.beeonetest.album.domain.model.album.AlbumResponseModel

@JsonClass(generateAdapter = true)
@Parcelize
data class AlbumResponseDtoItem(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "userId")
    val userId: Int?
) : Parcelable {
    fun toAlbumResponseModel(): AlbumResponseModel{
        return AlbumResponseModel(
            id = id ?:0, title = title?:"", userId = userId?:0
        )
    }
}