package com.traderevcoding.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("likeText")
fun setLikeTest(view: TextView, likes: Int) {
    view.text = "$likes likes"
}

@BindingAdapter("dateUpdated")
fun setDate(view: TextView, date: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val dateFormat = SimpleDateFormat("MMM dd yyyy", Locale.US)
    view.text = dateFormat.format(inputFormat.parse(date) ?: Date())
}