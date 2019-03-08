package com.sevenpeakssoftware.zawlynn.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.sevenpeakssoftware.zawlynn.model.CarModel
import com.sevenpeakssoftware.zawlynn.repository.APIRepository
import com.sevenpeakssoftware.zawlynn.repository.DatabaseRepository
import android.arch.paging.LivePagedListBuilder



class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var isFinished:MutableLiveData<Boolean>
    var success:MutableLiveData<Boolean>
    var cars:LiveData<PagedList<CarModel>>
    var db_repostory:DatabaseRepository
    var loading:MutableLiveData<Boolean>
    init {
        isFinished= MutableLiveData()
        success= MutableLiveData()
        loading= MutableLiveData()
        cars= MutableLiveData()
        db_repostory= DatabaseRepository.getInstance(application)
        cars= LivePagedListBuilder(db_repostory.allCars, 50).build();
    }
    fun getAllData(){
        val repository= APIRepository()
        repository.requestData()
        repository.loading_live.observeForever {
            loading.postValue(it)
        }
        repository.success_live.observeForever {
            success.postValue(it)
        }
        repository.getDataResponse().observeForever {
            if(!it.isNullOrEmpty()) {
                db_repostory.insertCars(it)
            }
        }
    }
}