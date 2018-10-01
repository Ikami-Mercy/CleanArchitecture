package com.gelostech.cleanarchitecture.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gelostech.cleanarchitecture.data.repositories.PostsRepository
import com.gelostech.cleanarchitecture.ui.viewmodels.PostsViewModel
import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {
    private lateinit var postsRepository: PostsRepository

    @Inject
    constructor(postsRepository: PostsRepository) {
        this.postsRepository = postsRepository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            return PostsViewModel(postsRepository) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}