package com.sevenpeakssoftware.zawlynn.model

import com.google.gson.annotations.SerializedName

class ContentModel {
    @SerializedName("type")
    var type: String? = null
    @SerializedName("subject")
    var subject: String? = null
    @SerializedName("description")
    var description: String? = null
}
