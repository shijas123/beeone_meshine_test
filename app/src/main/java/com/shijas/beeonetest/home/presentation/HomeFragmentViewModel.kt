package com.shijas.beeonetest.home.presentation

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
class HomeFragmentViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _getPost = MutableStateFlow<HomeState>(HomeState.Empty)
    val getPost = _getPost.asStateFlow()


    init {
        getPost()
    }

   private fun getPost() = viewModelScope.launch {
        homeRepository.getPosts().collect {
            when(it) {
                is Resource.Loading ->{
                    _getPost.emit(HomeState.Loading)
                }

                is Resource.Success ->{
                    _getPost.emit(HomeState.Success(it.value))
                }

                is Resource.Error ->{
                    _getPost.emit(HomeState.Error(it.error))
                }
            }
        }
    }
}