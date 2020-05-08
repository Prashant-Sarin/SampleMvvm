package com.sample.samplemvvm.application

import com.google.gson.GsonBuilder
import com.sample.samplemvvm.models.GitRepositoryModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    companion object {
        fun create(): Api {

            val gson = GsonBuilder()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(Api::class.java)
        }
    }


    @GET("/users/{user}/repos")
    fun reposForUser(@Path("user") user: String): Call<ArrayList<GitRepositoryModel>>
}