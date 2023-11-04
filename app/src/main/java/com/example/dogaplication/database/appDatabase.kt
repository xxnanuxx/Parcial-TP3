package com.example.dogaplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogaplication.entities.Perrito
@Database(entities = [Perrito::class], version = 1, exportSchema = false)
abstract class appDatabase : RoomDatabase() {
    abstract fun PerritoDao(): PerritoDao
    companion object {

        var INSTANCE: appDatabase? = null
        fun getAppDataBase(context: Context): appDatabase? {
            if (INSTANCE == null) {
                synchronized(appDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "myDB"
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