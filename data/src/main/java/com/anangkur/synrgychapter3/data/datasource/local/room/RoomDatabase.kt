package com.anangkur.synrgychapter3.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class RoomDatabase : RoomDatabase() {

    companion object {
        const val NAME = "room_database"
    }

    abstract fun movieDao(): MovieDao
}