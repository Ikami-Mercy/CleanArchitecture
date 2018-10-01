package com.gelostech.cleanarchitecture.di.modules

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.gelostech.cleanarchitecture.MyApplication
import com.gelostech.cleanarchitecture.data.AppDatabase
import com.gelostech.cleanarchitecture.data.daos.PostsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(app: MyApplication) {
    private var database: AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, "clean-architecture").build()

    @Provides
    @Singleton
    fun providesRoomDatabase(): AppDatabase {
        return database
    }

    @Provides
    @Singleton
    fun providesPostsDao(): PostsDao {
        return database.postsDao()
    }




}