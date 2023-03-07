package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Response

open class BaseRepository {

     fun failResponse(str: String): String{
        return Gson().fromJson(str, String::class.java)
    }

    fun <S>handleResponse(response: Response<S>, listener: APIListener<S>) {
        if (response.code() == TaskConstants.HTTP.SUCCESS) {
            response.body()?.let { listener.onSuccess(it) }
        } else {
            listener.onFaliure(failResponse(response.errorBody()!!.string()))
        }
    }


   fun <S> handleFaliure(listener: APIListener<S>, context: Context){
        listener.onFaliure(context.getString(R.string.ERROR_UNEXPECTED))
    }
}
