package com.shijas.beeonetest.album.prsentation.photos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijas.beeonetest.album.domain.repository.AlbumRepository
import com.shijas.beeonetest.album.prsentation.AlbumState
import com.shijas.beeonetest.common.Resource
import com.shijas.beeonetest.home.presentation.comment.CommentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PhotosFragmentViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    private var savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _getPhotos = MutableStateFlow<PhotosState>(PhotosState.Empty)
    val getPhotos = _getPhotos.asStateFlow()

    private val albumId = savedStateHandle.get<Int>("albumId")

    init {
        getPhoto()
    }

    private fun getPhoto() =viewModelScope.launch {

        if (albumId != null){
            albumRepository.getPhotos(albumId).collect{

                when(it) {

                    is Resource.Loading -> {
                        _getPhotos.emit(PhotosState.Loading)
                    }

                    is Resource.Success -> {
                        _getPhotos.emit(PhotosState.Success(it.value))
                    }

                    is Resource.Error -> {
                        _getPhotos.emit(PhotosState.Error(it.error))
                    }

                }
            }
        } else {
            _getPhotos.emit(PhotosState.Error("id null"))
        }

    }
}