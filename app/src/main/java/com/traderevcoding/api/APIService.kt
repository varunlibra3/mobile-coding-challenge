package com.traderevcoding.api
import com.traderevcoding.BuildConfig
import com.traderevcoding.data.UnsplashPhoto
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("photos")
    suspend fun getLatestPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ) : List<UnsplashPhoto>
}
