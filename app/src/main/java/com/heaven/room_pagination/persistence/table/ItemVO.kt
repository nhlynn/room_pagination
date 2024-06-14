package com.heaven.room_pagination.persistence.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemVO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)
