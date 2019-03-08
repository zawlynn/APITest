package com.sevenpeakssoftware.zawlynn.repository

import com.sevenpeakssoftware.zawlynn.api.ApiClient
import io.reactivex.disposables.CompositeDisposable
import com.google.gson.Gson
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.arch.lifecycle.MutableLiveData
import com.sevenpeakssoftware.zawlynn.model.CarModel
import com.sevenpeakssoftware.zawlynn.response.APIResponse


class APIRepository {
    private val client: ApiClient
    private val disposable: CompositeDisposable
    val loading_live = MutableLiveData<Boolean>()
    private val data = MutableLiveData<ArrayList<CarModel>>()
    val success_live = MutableLiveData<Boolean>()

    init {
        client = ApiClient()
        disposable = CompositeDisposable()
        loading_live.value = false
        success_live.value = false
    }

    fun requestData() {
        loading_live.value = true
        disposable.add(
            client.getRepositories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<String>() {
                    override fun onSuccess(s: String) {
                        loading_live.setValue(false)
                        try {
                            val response = Gson().fromJson<APIResponse>(s, APIResponse::class.java)
                            data.postValue(response.contents)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                    override fun onError(e: Throwable) {
                        loading_live.setValue(false)
                    }
                })
        )
    }

    fun getDataResponse(): MutableLiveData<ArrayList<CarModel>> {
        return data
    }
}