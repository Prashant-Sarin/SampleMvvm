package com.sample.samplemvvm.models

import com.google.gson.annotations.SerializedName

data class OwnerModel(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("login") var login: String,
    @SerializedName("url") var url: String,
    @SerializedName("avatar_url") var avatar_url: String
)