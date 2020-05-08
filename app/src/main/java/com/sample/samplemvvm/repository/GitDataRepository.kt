package com.sample.samplemvvm.repository

import com.sample.samplemvvm.application.Api
import com.sample.samplemvvm.application.ApiHandler
import com.sample.samplemvvm.listeners.RestCallback
import com.sample.samplemvvm.models.GitRepositoryModel

class GitDataRepository : ApiHandler() {

    private val TAG = GitRepositoryModel::class.java.simpleName

    private val api = Api.create()

    fun getReposFromApi(user: String, callback: RestCallback<ArrayList<GitRepositoryModel>>) {
        val call = api.reposForUser(user)
        makeApiCall(call, callback)
    }
}