package com.gelostech.cleanarchitecture.data.repositories

import com.gelostech.cleanarchitecture.data.models.Post
import com.gelostech.cleanarchitecture.utils.ApiService
import io.reactivex.Observable

class PostsRepository(private val apiService: ApiService) {

    fun fetchPosts(): Observable<List<Post>> {
        return apiService.getPosts()
    }
}