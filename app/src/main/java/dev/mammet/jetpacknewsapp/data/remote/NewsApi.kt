package dev.mammet.jetpacknewsapp.data.remote

import dev.mammet.jetpacknewsapp.data.remote.dto.NewsResponse
import dev.mammet.jetpacknewsapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("pages") page:Int,
        @Query("sources") sources:String,
        @Query("apiKey") apiKey: String = API_KEY
    ):NewsResponse
}