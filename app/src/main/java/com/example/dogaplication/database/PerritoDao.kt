package com.example.dogaplication.database
import androidx.room.*
import com.example.dogaplication.entities.Perrito

@Dao
interface PerritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerrito(perrito: Perrito?)

    @Update
    fun updatePerrito(perrito: Perrito?)

    @Delete
    fun deletePerrito(perrito: Perrito?)

    @Query("SELECT * FROM perritos ORDER BY id")
    fun loadAllPerritos(): MutableList<Perrito?>?

    @Query("SELECT * FROM perritos WHERE id = :id")
    fun loadPerritoById(id: Int): Perrito?

}