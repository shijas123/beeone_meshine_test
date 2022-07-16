package com.shijas.beeonetest.album.domain.model.album

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumResponseModel (
    val id : Int,
    val title: String,
    val userId: Int
        ) : Parcelable
