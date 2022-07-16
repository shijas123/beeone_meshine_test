package com.shijas.beeonetest.album.prsentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijas.beeonetest.album.domain.repository.AlbumRepository
import com.shijas.beeonetest.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AlbumFragmentViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
): ViewModel() {

    private val _getAlbum = MutableStateFlow<AlbumState>(AlbumState.Empty)
    val getAlbum = _getAlbum.asStateFlow()

    init {
        getAlbums()
    }

    private fun getAlbums() = viewModelScope.launch {
        albumRepository.getAlbum().collect{

            when(it){
                is Resource.Loading ->{
                    _getAlbum.emit(AlbumState.Loading)
                }

                is Resource.Success ->{
                    _getAlbum.emit(AlbumState.Success(it.value))
                }

                is Resource.Error ->{
                    _getAlbum.emit(AlbumState.Error(it.error))
                }
            }

        }
    }

}