package com.traderevcoding.data

import com.google.gson.annotations.SerializedName

data class UnsplashPhotoUrls (
    @field:SerializedName("small") val small: String,
    @field:SerializedName("regular") val regular: String,
    @field:SerializedName("thumb") val thumb: String
)
