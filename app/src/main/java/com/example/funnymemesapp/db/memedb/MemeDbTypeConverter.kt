package com.example.funnymemesapp.db.memedb

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.room.TypeConverter
import org.json.JSONArray
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class MemeDbTypeConverter {

    @TypeConverter
    fun fromJsonStringToArrayListOfString(value: String?): ArrayList<String>? {
        return try {
            val arrayList = ArrayList<String>()
            val json = JSONArray(value)
            for (i in 0 until json.length()) {
                arrayList.add(json.optString(i))
            }
            arrayList
        } catch (e: Exception) {
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


    @TypeConverter
    fun getBitmapFromString(byteArray: ByteArray?): Bitmap? {
        if (byteArray == null) return null
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    @TypeConverter
    fun getStringFromBitmap(bitmap: Bitmap?): ByteArray? {
        if (bitmap == null) {
            return ByteArray(10)
        }
        val outputStream = ByteArrayOutputStream()
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false)
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
}