package com.maschago.githubusersapp.utils

import com.airbnb.lottie.LottieCompositionFactory.fromJson
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.Response


val gson = Gson()

fun <T> toObject(jsonStr: String?, cls: Class<T>?): T? {
    if (jsonStr == null) return null
    return gson.fromJson(jsonStr, cls)
}


inline fun <reified T> fromJson(json: String) = gson.fromJson(json, T::class.java)

inline fun <reified T> Response<T>.getResponse(): T {
    val responseBody = body()
    return if (this.isSuccessful && responseBody != null) {
        responseBody
    } else {
        fromJson<T>(errorBody()!!.string())!!
    }
}