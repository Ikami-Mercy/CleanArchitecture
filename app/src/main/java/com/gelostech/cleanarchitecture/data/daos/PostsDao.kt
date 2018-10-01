package com.gelostech.cleanarchitecture.data.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.gelostech.cleanarchitecture.data.models.Post
import io.reactivex.Single

@Dao
interface PostsDao {

    @Query("SELECT * from posts LIMIT 20")
    fun getPosts(): Single<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)

}