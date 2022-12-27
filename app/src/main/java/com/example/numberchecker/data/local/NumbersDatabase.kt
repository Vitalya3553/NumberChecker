package com.example.numberchecker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.numberchecker.data.local.entity.ContactEntity

@Database(
    entities = [ContactEntity::class],
    version = 1
)
abstract class NumbersDatabase : RoomDatabase() {
    abstract val dao : NumberDao
}