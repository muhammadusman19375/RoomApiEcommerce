package com.example.designapp.Interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.designapp.RoomDB.User

@Dao
interface UserDao {
    @Insert
    fun insertAll(users: User)

    @Query("SELECT EXISTS(SELECT * FROM UserTable WHERE email =:email)")
    fun isTaken(email: String): Boolean

    @Query("SELECT * FROM UserTable WHERE email =:email AND password =  :password")
    fun getCredentials(email: String, password: String): Boolean

    @Query("SELECT first_name, last_name FROM UserTable WHERE email =:email ")
    fun getName(email: String): List<User>



}