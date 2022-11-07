package com.example.designapp.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    var retrofit: Retrofit? = null
    var BASEURL: String = "https://fakestoreapi.com/"

    @JvmName("getRetrofit1")
    fun getRetrofit(): Retrofit? {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}