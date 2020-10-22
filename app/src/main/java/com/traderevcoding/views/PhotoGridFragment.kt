package com.traderevcoding.views

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.traderevcoding.adapters.PhotoGridAdapter
import com.traderevcoding.api.ErrorType
import com.traderevcoding.api.Status
import com.traderevcoding.databinding.FragmentPhotoGridBinding
import com.traderevcoding.utils.InjectorUtils
import com.traderevcoding.utils.PaginationScrollListener
import com.traderevcoding.utils.PreferenceHelper
import com.traderevcoding.viewmodels.PhotoGridViewModel

class PhotoGridFragment : Fragment() {
    private val viewModel: PhotoGridViewModel by activityViewModels {
        InjectorUtils.providePhotoGridViewModelFactory()
    }
    private val adapter = PhotoGridAdapter()
    lateinit var binding: FragmentPhotoGridBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPhotoGridBinding.inflate(inflater, container, false)
        context ?: return binding.root

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        } else {
            binding.photoGrid.layoutManager = GridLayoutManager(context, 5)
        }

        binding.photoGrid.adapter = adapter
        registerScrollEndListener()
        subscribeLiveData()

        return binding.root
    }

    private fun subscribeLiveData() {
        viewModel.getAllPhotos().observe(viewLifecycleOwner) { liveData ->
            when (liveData.status) {
                Status.LOADING -> {
                    //we can show a progress bar here
                }
                Status.SUCCESS -> {
                    adapter.submitList(liveData.data?.toMutableList())
                }
                Status.ERROR -> {
                    val msg: String? = liveData.error?.let {
                        if (it.type == ErrorType.STRING) it.message else getString(it.messageId)
                    }
                    Snackbar.make(requireView(), msg ?: "", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun registerScrollEndListener() {
        binding.photoGrid.addOnScrollListener(object :
            PaginationScrollListener(binding.photoGrid.layoutManager as GridLayoutManager?) {
            override fun onLoadMore(pageNum: Int, recyclerView: RecyclerView?) {
                viewModel.loadPhotos()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lastPos = PreferenceHelper.getInt("imagePosition", 0)
        binding.photoGrid.post {
            binding.photoGrid.scrollToPosition(lastPos)
        }
    }
}