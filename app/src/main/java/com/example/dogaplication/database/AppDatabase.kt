package com.example.dogaplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogaplication.entities.Perrito
import com.example.dogaplication.entities.User
import com.example.dogaplication.entities.UserFavPerritoCrossRef

@Database(entities = [Perrito::class, User::class, UserFavPerritoCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun PerritoDao(): PerritoDao
    abstract fun UserDao(): UserDao
    abstract fun UserPerritoFavDao(): UserPerritoFavDao

    companion object {

        var INSTANCE: AppDatabase? = null
        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myDB2"
                    ).allowMainThreadQueries().build() // No es lo mas recomendable que se ejecute en el mainthread
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}