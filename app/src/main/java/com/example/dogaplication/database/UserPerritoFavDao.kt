package com.example.dogaplication.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dogaplication.entities.UserFavPerritoCrossRef

@Dao
interface UserPerritoFavDao {

    @Insert
    fun insertUserFavPerritoCrossRef(userPerritoCrossRef: UserFavPerritoCrossRef)

    @Query("DELETE FROM UserFavPerritoCrossRef WHERE userId = :userId AND perritoId = :perritoId")
    fun deleteUserFavPerritoCrossRef(userId : Int, perritoId : Int)

    @Query("SELECT * FROM UserFavPerritoCrossRef WHERE userId = :userId")
    fun getPerritosForUser(userId: Int?): MutableList<UserFavPerritoCrossRef>

    @Query("SELECT * FROM UserFavPerritoCrossRef WHERE userId = :userId AND perritoId = :perritoId")
    fun getFavorite(userId: Int?, perritoId : Int?): UserFavPerritoCrossRef

}