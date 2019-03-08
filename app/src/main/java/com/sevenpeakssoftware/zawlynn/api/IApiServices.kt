package com.sevenpeakssoftware.zawlynn.api


import io.reactivex.Single
import retrofit2.http.GET




interface IApiServices {
    @GET("article/get_articles_list")
    fun getDataFromAPI(): Single<String>
}