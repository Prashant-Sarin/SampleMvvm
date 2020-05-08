package com.sample.samplemvvm.listeners

interface RestCallback<T> {
    fun onSuccess(t:T?)
    fun onFailure(error: String)
}