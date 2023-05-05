package com.example.funnymemesapp.db.memedb

import androidx.room.TypeConverter
import org.json.JSONArray
import java.lang.Exception

class MemeDbTypeConverter {

    @TypeConverter
    fun fromJsonStringToArrayListOfString(value: String?): ArrayList<String>? {
        return try {
            val arrayList = ArrayList<String>()
            val json = JSONArray(value)
            for (i in 0 until json.length()){
                arrayList.add(json.optString(i))
            }
            arrayList
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    @TypeConverter
    fun fromArrayListOfStringToJsonString(data: ArrayList<String>): String? {
        val jsonArray = JSONArray()
        data.forEach {
            jsonArray.put(it)
        }
        return jsonArray.toString()
    }
}