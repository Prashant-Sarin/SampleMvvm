package com.sample.samplemvvm.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.samplemvvm.BR
import com.sample.samplemvvm.R
import com.sample.samplemvvm.databinding.ListItemBinding
import com.sample.samplemvvm.listeners.CustomClickListener
import com.sample.samplemvvm.models.GitRepositoryModel

class GitRepoAdapter(var context: Context, var gitRepoList: ArrayList<GitRepositoryModel>) :
    RecyclerView.Adapter<GitRepoAdapter.MyViewHolder>(),
    CustomClickListener {

    private val TAG = GitRepoAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listItemBinding = DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(context), R
                .layout.list_item, parent, false
        )
        return MyViewHolder(listItemBinding)
    }

    override fun getItemCount(): Int {
        return gitRepoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val gitRepo = gitRepoList[position]
        holder.bind(gitRepo)
        holder.listItemBinding.clickListener = this
    }

    inner class MyViewHolder(listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {
        var listItemBinding: ListItemBinding = listItemBinding
        fun bind(gitRepo: GitRepositoryModel) {
            listItemBinding.setVariable(BR.gitRepo, gitRepo)
            listItemBinding.executePendingBindings()
        }
    }

    override fun onItemClick(any: Any) {
        val repo = any as GitRepositoryModel
        Log.d(TAG, "item clicked: ${repo.name}")
    }
}