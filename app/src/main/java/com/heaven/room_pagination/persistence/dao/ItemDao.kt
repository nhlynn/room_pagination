package com.heaven.room_pagination.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heaven.room_pagination.persistence.table.ItemVO

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item:ItemVO):Long

    @Query("SELECT * FROM item ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getItem(limit: Int, offset: Int): List<ItemVO>
}