package com.sevenpeakssoftware.zawlynn.repository

import com.sevenpeakssoftware.zawlynn.api.ApiClient
import retrofit2.Retrofit

class APIRepository {

    companion object{
        var mInstance:APIRepository?=null
        fun getInstance():APIRepository{
            if(mInstance==null){
                mInstance= APIRepository()
            }
            return mInstance as APIRepository
        }
    }

    fun getRetrofitClient():Retrofit{
        return ApiClient.getInstance().getClient()!!
    }
}