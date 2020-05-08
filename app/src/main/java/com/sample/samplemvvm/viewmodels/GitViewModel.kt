package com.sample.samplemvvm.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.samplemvvm.listeners.RestCallback
import com.sample.samplemvvm.models.GitRepositoryModel
import com.sample.samplemvvm.repository.GitDataRepository

class GitViewModel : ViewModel() {

    private val TAG = GitViewModel::class.java.simpleName

    var gitRepoList = MutableLiveData<ArrayList<GitRepositoryModel>>()

    fun fetchLatestGitRepos(user: String) {
        val callback = object : RestCallback<ArrayList<GitRepositoryModel>> {
            override fun onSuccess(t: ArrayList<GitRepositoryModel>?) {
                Log.d(TAG, "getReposFromApi success value = $t")
                gitRepoList.postValue(t)
            }

            override fun onFailure(error: String) {
                Log.e(TAG, "getReposFromApi failed")
            }
        }
        GitDataRepository().getReposFromApi(user, callback)
    }

}