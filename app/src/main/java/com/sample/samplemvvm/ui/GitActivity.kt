package com.sample.samplemvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.samplemvvm.R
import com.sample.samplemvvm.databinding.ActivityGitBinding
import com.sample.samplemvvm.models.GitRepositoryModel
import com.sample.samplemvvm.ui.adapter.GitRepoAdapter
import com.sample.samplemvvm.viewmodels.GitViewModel

class GitActivity : AppCompatActivity() {

    private val TAG = GitActivity::class.java.simpleName

    private var binding: ActivityGitBinding? = null
    private var gitViewModel: GitViewModel? = null
    private var myAdapter: GitRepoAdapter? = null
    private var repoList = ArrayList<GitRepositoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding your layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_git)
        // getting viewModel
        gitViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(GitViewModel::class.java)
        // binding viewModel to view
        binding?.viewModel = gitViewModel
        // init observers to listen for changes in repoList
        initObservers()
        gitViewModel?.fetchLatestGitRepos("android")
    }

    private fun initObservers() {
        // observing gitRepoList Live data from viewModel
        gitViewModel?.gitRepoList?.observe(this, Observer {
            Log.d(TAG, "response from gitViewModel = $it")
            if (it.isNotEmpty()) {
                repoList.clear()
                repoList.addAll(it)
                if (binding?.myAdapter != null) {
                    binding?.myAdapter?.notifyDataSetChanged()
                } else {
                    myAdapter = GitRepoAdapter(this, repoList)
                    binding?.myAdapter = myAdapter
                }
            }
        })
    }
}
