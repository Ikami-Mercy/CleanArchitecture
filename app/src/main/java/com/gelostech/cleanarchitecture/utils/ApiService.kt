package com.gelostech.cleanarchitecture.utils

import com.gelostech.cleanarchitecture.data.models.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET(Endpoints.POSTS_URL)
    fun getPosts(): Observable<List<Post>>

}