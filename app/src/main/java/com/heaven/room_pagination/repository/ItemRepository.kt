package com.heaven.room_pagination.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.heaven.room_pagination.paging.ItemPagingSource
import com.heaven.room_pagination.persistence.dao.ItemDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository
@Inject constructor(private val dao: ItemDao) {
    fun getItem() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { ItemPagingSource(dao) }
        ).flow
}