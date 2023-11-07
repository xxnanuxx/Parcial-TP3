package com.example.dogaplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dogaplication.entities.UserFavPerritoCrossRef

@Dao
interface UserPerritoFavDao {

    @Insert
    fun insertUserFavPerritoCrossRef(userPerritoCrossRef: UserFavPerritoCrossRef)

    @Query("SELECT * FROM UserFavPerritoCrossRef WHERE userId = :userId")
    fun getPerritosForUser(userId: Int?): List<UserFavPerritoCrossRef>

}