package com.sevenpeakssoftware.zawlynn.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sevenpeakssoftware.zawlynn.model.CarModel
import io.reactivex.Flowable

@Dao
interface CarDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data:List<CarModel>)

    @Query("SELECT * FROM tbl_car")
    fun selectAll(): Flowable<List<CarModel>>
}