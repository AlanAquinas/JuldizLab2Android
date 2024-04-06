package com.example.multiscreenapp.network

import com.example.multiscreenapp.model.Celebrity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface CelebrityService {
    @GET("celebrity")
    fun fetchCelebrityList(@Query("name") name: String): Call<ArrayList<Celebrity>>

}