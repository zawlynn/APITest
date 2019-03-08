package com.sevenpeakssoftware.zawlynn.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sevenpeakssoftware.zawlynn.model.converter.KContentTypeConverters

@Entity(tableName = "tbl_car")
@TypeConverters(KContentTypeConverters::class)
class CarModel {
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Long? = null
    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null
    @SerializedName("dateTime")
    @ColumnInfo(name = "dateTime")
    var dateTime: String? = null
    @SerializedName("ingress")
    @ColumnInfo(name = "ingress")
    var ingress: String? = null
    @SerializedName("image")
    @ColumnInfo(name = "image")
    var image: String? = null
    @SerializedName("created")
    @ColumnInfo(name = "created")
    var created: Long? = null
    @SerializedName("changed")
    @ColumnInfo(name = "changed")
    var changed: Long? = null
    @SerializedName("content")
    @ColumnInfo(name = "contents")
    var contents: List<ContentModel>? = null
}