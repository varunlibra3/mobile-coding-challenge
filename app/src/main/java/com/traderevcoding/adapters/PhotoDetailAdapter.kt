package com.traderevcoding.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.traderevcoding.views.SinglePhotoDetailFragment

class PhotoDetailAdapter(fragment: Fragment, private val itemsCount: Int): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = SinglePhotoDetailFragment()
        fragment.arguments = Bundle().apply {
            putInt(SinglePhotoDetailFragment.ARG_POSITION, position)
        }

        return fragment
    }
}