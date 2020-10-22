package com.traderevcoding.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.traderevcoding.adapters.PhotoDetailAdapter
import com.traderevcoding.databinding.FragmentPhotoDetailBinding
import com.traderevcoding.utils.InjectorUtils
import com.traderevcoding.viewmodels.PhotoGridViewModel

class PhotoDetailFragment : Fragment() {
    private val args: PhotoDetailFragmentArgs by navArgs()
    private val viewModel: PhotoGridViewModel by activityViewModels {
        InjectorUtils.providePhotoGridViewModelFactory()
    }
    lateinit var binding: FragmentPhotoDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PhotoDetailAdapter(this, viewModel.photoList.size)
        binding.pager.adapter = adapter

        binding.pager.post {
            savedInstanceState?.let {
                binding.pager.setCurrentItem(it.getInt("lastPos", args.photoPos), false)
            } ?: let {
                binding.pager.setCurrentItem(args.photoPos, false)
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("lastPos", binding.pager.currentItem) //saving last photo position in case of orientation change
    }
}