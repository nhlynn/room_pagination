package com.heaven.room_pagination.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.heaven.room_pagination.persistence.dao.ItemDao
import com.heaven.room_pagination.persistence.table.ItemVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ItemVO::class],
    version = 1,
    exportSchema = false
)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun getItem(): ItemDao

    companion object {
        private var dbINSTANCE: ItemDatabase? = null

        fun getAppDB(context: Context): ItemDatabase {
            return dbINSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "item_database"
                ).allowMainThreadQueries()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            dbINSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    (0..300).forEach {
                                        database.getItem().insertItem(ItemVO(0, "Item $it"))
                                    }
                                }
                            }
                        }
                    })
                    .build()
                dbINSTANCE = instance
                instance
            }
        }
    }
}