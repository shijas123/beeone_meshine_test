package com.shijas.beeonetest.home.presentation.comment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shijas.beeonetest.common.Resource
import com.shijas.beeonetest.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentFragmentViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private var savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _getComment = MutableStateFlow<CommentState>(CommentState.Empty)
    val getComment = _getComment.asStateFlow()

    private val postId = savedStateHandle.get<Int>("postId")

    init {
        getComment()
    }

    private fun getComment() = viewModelScope.launch {

        if (postId !=null){

            homeRepository.getComments(postId).collect{
                when(it) {
                    is Resource.Loading -> {
                        _getComment.emit(CommentState.Loading)
                    }

                    is Resource.Success -> {
                        _getComment.emit(CommentState.Success(it.value))
                    }

                    is Resource.Error -> {
                        _getComment.emit(CommentState.Error(it.error))
                    }
                }
            }

        } else {
            _getComment.emit(CommentState.Error("id null"))
        }

    }
}