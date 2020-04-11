package com.example.nybooks.data

import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {

    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "dYvSDzxECNAYUvJ2btXR2CyNnbUwSTOs",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}