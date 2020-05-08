package com.sample.samplemvvm.application

import com.sample.samplemvvm.listeners.RestCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ApiHandler {

    fun <T> makeApiCall(call: Call<T>, callback: RestCallback<T>?) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                callback?.onFailure(t.toString())
            }

            override fun onResponse(call: Call<T>, response: Response<T>?) {
                val responseBody = response?.body()
                if (response != null && response.isSuccessful) {
                    callback?.onSuccess(responseBody)
                }
            }
        })

    }
}