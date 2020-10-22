package com.traderevcoding.data

import com.google.gson.annotations.SerializedName

data class UnsplashPhoto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: UnsplashPhotoUrls,
    @field:SerializedName("user") val user: UnsplashUser,
    @field:SerializedName("likes") val likes: Int,
    @field:SerializedName("created_at") val createdAt: String,
)
