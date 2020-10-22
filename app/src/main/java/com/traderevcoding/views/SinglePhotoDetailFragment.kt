package com.traderevcoding.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.traderevcoding.databinding.ListItemPhotoDetailBinding
import com.traderevcoding.utils.InjectorUtils
import com.traderevcoding.utils.PreferenceHelper
import com.traderevcoding.viewmodels.PhotoGridViewModel

class SinglePhotoDetailFragment : Fragment() {
    private val viewModel: PhotoGridViewModel by activityViewModels {
        InjectorUtils.providePhotoGridViewModelFactory()
    }

    companion object {
        const val ARG_POSITION = "position"
    }

    private lateinit var binding: ListItemPhotoDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ListItemPhotoDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_POSITION) }?.apply {
            val unsplashPhoto = viewModel.photoList[getInt(ARG_POSITION)]
            binding.apply {
                photo = unsplashPhoto
                executePendingBindings()
            }

            saveLastPosition(getInt(ARG_POSITION))
        }
    }

    private fun saveLastPosition(curPos: Int) {
        PreferenceHelper.setInt("imagePosition", curPos)
    }
}