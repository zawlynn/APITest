package com.sevenpeakssoftware.zawlynn.model.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sevenpeakssoftware.zawlynn.model.ContentModel

class KContentTypeConverters {
    @TypeConverter
    fun stringToContents(json: String): List<ContentModel>? {
        val gson = Gson()
        val type = object : TypeToken<List<ContentModel>>() {

        }.type
        return gson.fromJson<List<ContentModel>>(json, type)
    }

    @TypeConverter
    fun contentToString(list: List<ContentModel>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ContentModel>>() {

        }.type
        return gson.toJson(list, type)
    }
}