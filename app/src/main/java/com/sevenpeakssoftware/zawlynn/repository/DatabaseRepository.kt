package com.sevenpeakssoftware.zawlynn.repository

import android.app.Application
import android.arch.paging.DataSource
import android.os.AsyncTask
import com.sevenpeakssoftware.zawlynn.dao.CarDAO
import com.sevenpeakssoftware.zawlynn.database.KCloudDatabase
import com.sevenpeakssoftware.zawlynn.model.CarModel
import java.util.ArrayList

class DatabaseRepository private constructor(application: Application) {
    val allCars: DataSource.Factory<Int, CarModel>
        get() = dao.selectAll()

    init {
        val cloudDatabase = KCloudDatabase.getInstance(application)
        dao = cloudDatabase.carDAO()
    }

    fun insertCars(data: ArrayList<CarModel>) {
        InsertCarsAsyncTask(dao, data).execute()
    }

    private class InsertCarsAsyncTask internal constructor(
        private val dao: CarDAO,
        internal var lists: ArrayList<CarModel>
    ) : AsyncTask<Void, Void, Void>() {
        @SafeVarargs
        override fun doInBackground(vararg voids: Void): Void? {
            dao.insertAll(lists)
            return null
        }
    }

    companion object {
        private lateinit var dao: CarDAO
        private var instance: DatabaseRepository? = null
        fun getInstance(application: Application): DatabaseRepository {
            if (instance == null) {
                instance = DatabaseRepository(application)
                val cloudDatabase = KCloudDatabase.getInstance(application)
                dao = cloudDatabase.carDAO()
            }
            return instance as DatabaseRepository
        }
    }
}
