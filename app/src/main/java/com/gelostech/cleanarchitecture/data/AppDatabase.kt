package com.gelostech.cleanarchitecture.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.gelostech.cleanarchitecture.data.daos.PostsDao
import com.gelostech.cleanarchitecture.data.models.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

}