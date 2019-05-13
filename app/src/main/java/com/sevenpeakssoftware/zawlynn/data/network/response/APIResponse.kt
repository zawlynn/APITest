package com.sevenpeakssoftware.zawlynn.data.network.response

import com.google.gson.annotations.SerializedName
import com.sevenpeakssoftware.zawlynn.model.CarModel

class APIResponse{
    @SerializedName("status")
    var status:String?=null
    @SerializedName("content")
    var contents:ArrayList<CarModel>
    @SerializedName("serverTime")
    var serverTime:Long?=null
    init {
        contents= ArrayList()
    }
}