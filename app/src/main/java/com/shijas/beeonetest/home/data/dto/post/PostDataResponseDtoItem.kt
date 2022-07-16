package com.shijas.beeonetest.home.data.dto.post


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel

@JsonClass(generateAdapter = true)
@Parcelize
data class PostDataResponseDtoItem(
    @Json(name = "body")
    val body: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "userId")
    val userId: Int?
) : Parcelable {

    fun toPostResponseModel() : PostResponseModel {
        return PostResponseModel(
            body = body ?:"", id = id ?:0, title = title ?: "", userId = userId ?: 0
        )
    }

}