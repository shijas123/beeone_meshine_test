package com.shijas.beeonetest.home.presentation.comment

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
import com.shijas.beeonetest.databinding.ActivityMainBinding
import com.shijas.beeonetest.databinding.FragmentCommentBinding
import com.shijas.beeonetest.home.presentation.HomeFragmentViewModel
import com.shijas.beeonetest.home.presentation.ItemPostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.annotation.meta.When

@AndroidEntryPoint
class CommentFragment : Fragment(R.layout.fragment_comment) {
    private  lateinit var binding: FragmentCommentBinding
    private val viewModel by viewModels<CommentFragmentViewModel>()
    private val commentItemAdapter by lazy { ItemCommentAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCommentBinding.bind(view)

        setUpRecyclerView()
        observeCommentResp()
    }

    private fun setUpRecyclerView() {
        binding.commentRecycler.apply { adapter = commentItemAdapter }
    }

    private fun observeCommentResp(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getComment.collect{
                    when(it){

                        is CommentState.Loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is CommentState.Success -> {
                            binding.progressBar.isVisible = false
                            commentItemAdapter.submitList(it.showComment)
                        }

                        is CommentState.Error -> {
                            binding.progressBar.isVisible = false
                            Toast.makeText(requireContext(),it.message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }

            }
        }
    }

}