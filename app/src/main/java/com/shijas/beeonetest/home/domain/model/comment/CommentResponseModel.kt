package com.shijas.beeonetest.home.domain.model.comment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentResponseModel(

    val body : String,
    val email : String,
    val id : Int,
    val name : String,
    val postId : Int

) : Parcelable
