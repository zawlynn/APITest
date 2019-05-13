package com.sevenpeakssoftware.zawlynn.repository

import android.app.Application
import android.arch.paging.DataSource
import android.os.AsyncTask
import com.sevenpeakssoftware.zawlynn.dao.CarDAO
import com.sevenpeakssoftware.zawlynn.data.database.KCloudDatabase
import com.sevenpeakssoftware.zawlynn.model.CarModel
import io.reactivex.Flowable
import java.util.ArrayList

class DatabaseRepository(val application: Application) {
    fun saveCars(repos: List<CarModel>) {
        KCloudDatabase.getInstance(application).carDAO().insertAll(repos)
    }

    fun fetchLocalData(): Flowable<List<CarModel>> {
        return KCloudDatabase.getInstance(application).carDAO().selectAll()
    }
}
