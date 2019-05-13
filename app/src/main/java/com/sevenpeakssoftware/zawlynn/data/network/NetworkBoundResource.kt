package com.sevenpeakssoftware.zawlynn.data.network

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class NetworkBoundResource<RequestType, ResultType>(context: Context) {
    private val result: Flowable<Resource<ResultType>>

    init {
        val diskObservable = Flowable.defer {
            loadFromDb()
                .subscribeOn(Schedulers.computation())
        }

        val networkObservable = Flowable.defer {
            createCall()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnNext { request: Response<RequestType> ->
                    if (request.isSuccessful) {
                        saveCallResult(processResponse(request))
                    }
                }
                .onErrorReturn { throwable: Throwable ->
                    when (throwable) {
                        is HttpException -> {
                            throw Exceptions.propagate(throwable)
                        }
                        is IOException -> {
                            throw Exceptions.propagate(throwable)
                        }
                        else -> {
                            throw Exceptions.propagate(throwable)
                        }
                    }
                }
                .flatMap { loadFromDb() }
        }

        result = when {
            context.isNetworkStatusAvailable() -> networkObservable
                .map<Resource<ResultType>> {
                    Resource.success(it)
                }
                .onErrorReturn {
                    Resource.error(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(Resource.loading())

            else -> diskObservable
                .map<Resource<ResultType>> { Resource.success(it) }
                .onErrorReturn { Resource.error(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(Resource.loading())
        }
    }

    fun asFlowable(): Flowable<Resource<ResultType>> {
        return result
    }

    private fun processResponse(response: Response<RequestType>): RequestType {
        return response.body()!!
    }

    protected abstract fun saveCallResult(request: RequestType)

    protected abstract fun loadFromDb(): Flowable<ResultType>

    protected abstract fun createCall(): Flowable<Response<RequestType>>

}

fun Context.isNetworkStatusAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        it.activeNetworkInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}