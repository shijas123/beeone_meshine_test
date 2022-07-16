package com.shijas.beeonetest.home.domain.model.post

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostResponseModel(
val body : String,
val id : Int,
val title: String,
val userId : Int
) : Parcelable
