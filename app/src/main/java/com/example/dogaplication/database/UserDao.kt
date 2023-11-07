package com.example.dogaplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogaplication.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User?)

    @Query("SELECT * FROM users WHERE id = :id")
    fun loadUserById(id: Int): User?

    @Query("SELECT * FROM users WHERE usuario = :usuario")
    fun loadUserByUsername(usuario: String): User?

    @Query("SELECT COUNT(*) FROM users")
    fun countUsers(): Int?

}