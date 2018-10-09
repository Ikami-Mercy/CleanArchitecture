package com.gelostech.cleanarchitecture.ui.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gelostech.cleanarchitecture.R
import com.gelostech.cleanarchitecture.data.Status
import com.gelostech.cleanarchitecture.data.response.PostsResponse
import com.gelostech.cleanarchitecture.di.Injectable
import com.gelostech.cleanarchitecture.di.ViewModelFactory
import com.gelostech.cleanarchitecture.ui.adapters.PostsAdapter
import com.gelostech.cleanarchitecture.ui.viewmodels.PostsViewModel
import com.gelostech.cleanarchitecture.utils.hideView
import com.gelostech.cleanarchitecture.utils.showView
import kotlinx.android.synthetic.main.fragment_posts.*
import timber.log.Timber
import javax.inject.Inject

class PostsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewmodel: PostsViewModel
    private lateinit var adapter: PostsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(activity)

        adapter = PostsAdapter()
        rv.adapter = adapter

        viewmodel.posts().observe(this, Observer<PostsResponse> {
            handleResponse(it!!)
        })
        viewmodel.fetchPosts()
    }

    private fun handleResponse(response: PostsResponse) {
        when(response.status) {
            Status.LOADING -> {
                Timber.e("Loading posts...")
                loading.showView()
            }

            Status.SUCCESS -> {
                Timber.e("Fetched posts")
                loading.hideView()
                adapter.addPosts(response.posts!!)
            }

            Status.ERROR -> {
                Timber.e("Error fetching posts")
                loading.hideView()
                error.showView()
            }
        }
    }


}
