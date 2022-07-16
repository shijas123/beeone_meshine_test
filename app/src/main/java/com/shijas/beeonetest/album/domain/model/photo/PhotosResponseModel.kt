package com.shijas.beeonetest.album.domain.model.photo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotosResponseModel(
    val albumId: Int,
    val id: Int,
    val thumbnail: String,
    val title : String,
    val url : String
): Parcelable
