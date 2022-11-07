package com.example.designapp.Interfaces

import com.example.designapp.Retrofit.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    fun getDetails(): Call<List<ProductsResponse>>
}