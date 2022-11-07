package com.example.designapp.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.designapp.Interfaces.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}