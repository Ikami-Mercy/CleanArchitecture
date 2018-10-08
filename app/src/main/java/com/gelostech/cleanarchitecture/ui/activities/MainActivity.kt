package com.gelostech.cleanarchitecture.ui.activities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import com.gelostech.cleanarchitecture.MyApplication
import com.gelostech.cleanarchitecture.R
import com.gelostech.cleanarchitecture.data.Status
import com.gelostech.cleanarchitecture.data.response.PostsResponse
import com.gelostech.cleanarchitecture.di.ViewModelFactory
import com.gelostech.cleanarchitecture.ui.adapters.PostsAdapter
import com.gelostech.cleanarchitecture.ui.viewmodels.PostsViewModel
import com.gelostech.cleanarchitecture.utils.hideView
import com.gelostech.cleanarchitecture.utils.showView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasActivityInjector {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    private lateinit var viewmodel: PostsViewModel
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(PostsViewModel::class.java)

        initViews()

        viewmodel.posts().observe(this, Observer<PostsResponse> {
            handleResponse(it!!)
        })
        viewmodel.fetchPosts()


    }

    private fun initViews() {
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        adapter = PostsAdapter()
        rv.adapter = adapter
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

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}
