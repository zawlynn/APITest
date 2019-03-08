package com.sevenpeakssoftware.zawlynn.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.sevenpeakssoftware.zawlynn.dao.CarDAO
import com.sevenpeakssoftware.zawlynn.model.CarModel

@Database(entities = [CarModel::class], version = 1)
abstract class KCloudDatabase : RoomDatabase() {
    abstract fun carDAO(): CarDAO

    companion object {
        private var instance: KCloudDatabase? = null
        @Synchronized
        fun getInstance(context: Context): KCloudDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    KCloudDatabase::class.java, "car_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as KCloudDatabase
        }
    }
}
