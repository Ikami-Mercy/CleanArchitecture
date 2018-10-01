package com.gelostech.cleanarchitecture.ui.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gelostech.cleanarchitecture.data.repositories.PostsRepository
import com.gelostech.cleanarchitecture.data.response.PostsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PostsViewModel(private val postsRepository: PostsRepository) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val responseLiveData = MutableLiveData<PostsResponse>()

    fun posts(): MutableLiveData<PostsResponse> {
        return responseLiveData
    }

    fun fetchPosts() {

        disposables.add(postsRepository.fetchPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { responseLiveData.value = PostsResponse.loading() }
                .subscribe({
                    Timber.e("Fetched posts: ${it.size}")
                    responseLiveData.value = PostsResponse.success(it)

                }, {
                    Timber.e("Error fetching posts: ${it.localizedMessage}")
                    responseLiveData.value = PostsResponse.error(it)

                }))

    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}