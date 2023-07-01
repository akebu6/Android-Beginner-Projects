package com.example.quotesapp.networking

import retrofit2.Call
import retrofit2.http.GET

interface JSONDataRequest {

    @GET("random?tags=technology,famous-quotes")

    fun getQuotes() : Call<RandomQuote>

}