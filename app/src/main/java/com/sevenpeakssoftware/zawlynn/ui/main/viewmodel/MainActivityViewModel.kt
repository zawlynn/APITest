package com.sevenpeakssoftware.zawlynn.ui.main.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.sevenpeakssoftware.zawlynn.model.CarModel
import com.sevenpeakssoftware.zawlynn.repository.APIRepository
import com.sevenpeakssoftware.zawlynn.repository.DatabaseRepository
import android.arch.paging.LivePagedListBuilder
import android.content.Context
import android.util.Log
import com.sevenpeakssoftware.zawlynn.data.network.Resource
import com.sevenpeakssoftware.zawlynn.repository.CarRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var isFinished:MutableLiveData<Boolean>
    var success:MutableLiveData<Boolean>
    var cars:MutableLiveData<List<CarModel>>
    var loading:MutableLiveData<Boolean>
    var apiRepo:APIRepository
    var databaseRepo:DatabaseRepository
    var repository:CarRepository
    var compositeDisposable = CompositeDisposable()
    init {
        isFinished= MutableLiveData()
        success= MutableLiveData()
        loading= MutableLiveData()
        cars= MutableLiveData()
        apiRepo= APIRepository.getInstance()
        databaseRepo=DatabaseRepository(application)
        repository= CarRepository(apiRepo,databaseRepo)
    }
    fun getAllData(context: Context){
       repository.getDataFromApi(context)
           .doOnError {
               Log.d("ERROR OCCUR",it.message)
           }
           .subscribe {
                it?.let {
                    it.data?.let {
                        cars.postValue(it)
                    }
                    if(it.status== Resource.Status.LOADING){
                        Log.d("CAR REPOSITORY","LOADING")
                    }else if(it.status==Resource.Status.SUCCESS){
                        Log.d("CAR REPOSITORY","SUCCESS")
                    }else {
                        Log.d("CAR REPOSITORY","ERROR")
                    }
                }
           }.addTo(compositeDisposable)


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getCar(): LiveData<List<CarModel>> {
        return cars
    }
}