package com.sevenpeakssoftware.zawlynn.api


import com.sevenpeakssoftware.zawlynn.data.network.Resource
import com.sevenpeakssoftware.zawlynn.data.network.response.APIResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import java.net.ResponseCache


interface IApiServices {
    @GET("article/get_articles_list")
    fun getDataFromAPI(): Flowable<Response<APIResponse>>
}