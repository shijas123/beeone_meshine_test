package com.shijas.beeonetest.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.shijas.beeonetest.R
import com.shijas.beeonetest.common.Resource
import com.shijas.beeonetest.databinding.FragmentHomeBinding
import com.shijas.beeonetest.home.domain.model.post.PostResponseModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) , ItemPostAdapter.OnClick {
    private val viewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private val postItemAdapter by lazy {ItemPostAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding =  FragmentHomeBinding.bind(view)

        setUpRecyclerView()

        observePostResp()
    }

    private fun setUpRecyclerView() {
        binding.postRecycler.apply { adapter = postItemAdapter }
    }

    private fun observePostResp() {
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getPost.collect{

                    when(it){

                        is HomeState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is HomeState.Success -> {
                            binding.progressBar.isVisible = false
                            postItemAdapter.submitList(it.showPost)
                        }

                        is HomeState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                        }


                    }

                }
            }
        }
    }

    override fun onPostShowClick(postResponseModel: PostResponseModel) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToCommentFragment(postResponseModel.id))
    }

}