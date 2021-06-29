package com.example.myapplication.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.DAO.MovieDao
import com.example.myapplication.Entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}