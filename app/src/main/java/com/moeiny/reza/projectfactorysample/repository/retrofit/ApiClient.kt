package com.moeiny.reza.projectfactorysample.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    fun getClient():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://najva.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}