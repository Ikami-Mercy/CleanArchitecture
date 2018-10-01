package com.gelostech.cleanarchitecture.data.repositories

import com.gelostech.cleanarchitecture.data.daos.PostsDao
import com.gelostech.cleanarchitecture.data.models.Post
import com.gelostech.cleanarchitecture.utils.ApiService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PostsRepository(private val apiService: ApiService, private val postsDao: PostsDao) {

    fun fetchPosts(): Observable<List<Post>> {
        return Observable.concatArray(
                fetchPostsFromRoom(),
                fetchPostsFromNet()
                        .materialize()
                        .filter { !it.isOnError }
                        .dematerialize<List<Post>>()
        )
    }

    private fun fetchPostsFromRoom(): Observable<List<Post>> {
        return postsDao.getPosts().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Timber.e("Fetched from DB: ${it.size}")
                }
    }

    private fun fetchPostsFromNet(): Observable<List<Post>> {
        return apiService.getPosts()
                .filter { it.isNotEmpty() }
                .doOnNext {
                    Timber.e("Fetched from API: ${it.size}")
                    savePosts(it)
                }
    }

    private fun savePosts(posts: List<Post>) {
        Observable.fromCallable { postsDao.insertAll(posts) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.e("Saved ${posts.size} posts from the net")
                }
    }

}