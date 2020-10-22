package com.traderevcoding.api

class APIHelper(private val apiService: APIService) {
    suspend fun getLatestPhotos(page: Int, perPage: Int) = apiService.getLatestPhotos(page, perPage)
}