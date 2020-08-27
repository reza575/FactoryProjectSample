package com.moeiny.reza.projectfactorysample.repository.retrofit

import com.moeiny.reza.projectfactorysample.repository.model.Decision
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("reza.json")
    fun getDecisonsInfo():Call<List<Decision>>
}