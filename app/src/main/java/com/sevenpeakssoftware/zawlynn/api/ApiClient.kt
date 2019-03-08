package com.sevenpeakssoftware.zawlynn.api

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {
    val retrofit:Retrofit
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.apphusetreach.no/application/119267/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
    fun getClient(): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return retrofit
    }
    fun getRepositories(): Single<String> {

        val service = retrofit.create(IApiServices::class.java)
        val data = service.getDataFromAPI()
        return data
    }
}