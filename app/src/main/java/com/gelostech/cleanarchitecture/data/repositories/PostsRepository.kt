package com.gelostech.cleanarchitecture.data.repositories

import android.annotation.SuppressLint
import com.gelostech.cleanarchitecture.data.daos.PostsDao
import com.gelostech.cleanarchitecture.data.models.Post
import com.gelostech.cleanarchitecture.utils.ApiService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject constructor(private val apiService: ApiService, private val postsDao: PostsDao) {

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

    @SuppressLint("CheckResult")
    private fun savePosts(posts: List<Post>) {
        Observable.fromCallable { postsDao.insertAll(posts) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Timber.e("Saved ${posts.size} posts from the net")
                }
    }

}