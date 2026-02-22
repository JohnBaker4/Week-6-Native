package com.example.week6native.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.week6native.data.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}