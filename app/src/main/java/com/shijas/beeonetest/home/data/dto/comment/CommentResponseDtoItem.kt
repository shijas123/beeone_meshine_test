package com.shijas.beeonetest.home.data.dto.comment


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.shijas.beeonetest.home.domain.model.comment.CommentResponseModel

@JsonClass(generateAdapter = true)
@Parcelize
data class CommentResponseDtoItem(
    @Json(name = "body")
    val body: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "postId")
    val postId: Int?
) : Parcelable {
    fun toCommentRespModel() : CommentResponseModel{
        return CommentResponseModel(
            body = body ?:"", email = email?: "", id = id ?:0, name = name?:"", postId = postId?:0
        )
    }
}