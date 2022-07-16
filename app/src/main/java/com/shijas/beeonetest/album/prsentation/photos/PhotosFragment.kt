package com.shijas.beeonetest.album.prsentation.photos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.shijas.beeonetest.R
import com.shijas.beeonetest.album.prsentation.AlbumFragmentViewModel
import com.shijas.beeonetest.album.prsentation.ItemAlbumAdapter
import com.shijas.beeonetest.databinding.FragmentPhotosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : Fragment(R.layout.fragment_photos) {
    private lateinit var binding: FragmentPhotosBinding
    private val viewModel by viewModels<PhotosFragmentViewModel>()
    private val photoItemAdapter by lazy { ItemPhotoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPhotosBinding.bind(view)

        setUpRecyclerView()
        observePhotoResponse()
    }

    private fun setUpRecyclerView() {
        binding.photosRecycler.apply { adapter = photoItemAdapter }
    }

    private fun observePhotoResponse(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getPhotos.collect{
                    when(it){

                        is PhotosState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is PhotosState.Success -> {
                            binding.progressBar.isVisible = false
                            photoItemAdapter.submitList(it.showPhotos)
                        }

                        is PhotosState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }


}