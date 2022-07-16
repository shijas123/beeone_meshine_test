package com.shijas.beeonetest.album.prsentation

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
import androidx.navigation.fragment.findNavController
import com.shijas.beeonetest.R
import com.shijas.beeonetest.album.domain.model.album.AlbumResponseModel
import com.shijas.beeonetest.databinding.FragmentAlbumBinding
import com.shijas.beeonetest.home.presentation.HomeFragmentViewModel
import com.shijas.beeonetest.home.presentation.ItemPostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumFragment : Fragment(R.layout.fragment_album) , ItemAlbumAdapter.OnClick {
    private lateinit var binding: FragmentAlbumBinding
    private val viewModel by viewModels<AlbumFragmentViewModel>()
    private val albumItemAdapter by lazy { ItemAlbumAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAlbumBinding.bind(view)
        setUpRecyclerView()
        observeAlbumResponse()
    }
    private fun setUpRecyclerView() {
        binding.albumRecycler.apply { adapter = albumItemAdapter }
    }

    private fun observeAlbumResponse(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getAlbum.collect{
                    when(it) {
                        is AlbumState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is AlbumState.Success -> {
                            binding.progressBar.isVisible = false
                            albumItemAdapter.submitList(it.showAlbum)
                        }

                        is AlbumState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onPhotoShowClick(albumResponseModel: AlbumResponseModel) {
        findNavController().navigate(AlbumFragmentDirections.actionAlbumFragmentToPhotosFragment(albumResponseModel.id))
    }
}