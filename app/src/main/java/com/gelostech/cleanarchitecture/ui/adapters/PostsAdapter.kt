package com.gelostech.cleanarchitecture.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gelostech.cleanarchitecture.R
import com.gelostech.cleanarchitecture.data.models.Post
import com.gelostech.cleanarchitecture.databinding.ItemPostBinding
import com.gelostech.cleanarchitecture.utils.inflate
import timber.log.Timber

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostHolder>() {
    private val posts = mutableListOf<Post>()

    fun addPosts(posts: List<Post>) {
        this.posts.addAll(posts)
        notifyDataSetChanged()

        Timber.e("Posts count: $itemCount")
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostHolder {
        return PostHolder(p0.inflate(R.layout.item_post))
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(p0: PostHolder, p1: Int) {
        p0.bind(posts[p1])
    }

    class PostHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
        }

    }

}