package com.example.dogaplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogaplication.entities.Perrito
import com.example.dogaplication.entities.User
@Database(entities = [Perrito::class, User::class], version = 1, exportSchema = false)
abstract class appDatabase : RoomDatabase() {

    abstract fun PerritoDao(): PerritoDao

    abstract fun UserDao(): UserDao

    companion object {

        var INSTANCE: appDatabase? = null
        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
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