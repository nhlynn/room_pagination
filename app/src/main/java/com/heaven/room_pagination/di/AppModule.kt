package com.heaven.room_pagination.di

import android.app.Application
import com.heaven.room_pagination.persistence.ItemDatabase
import com.heaven.room_pagination.persistence.dao.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun getAppDB(context: Application): ItemDatabase {
        return ItemDatabase.getAppDB(context)
    }

    @Singleton
    @Provides
    fun getAppDao(appDB: ItemDatabase): ItemDao {
        return appDB.getItem()
    }
}