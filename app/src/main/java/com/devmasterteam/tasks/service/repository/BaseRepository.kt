package com.devmasterteam.tasks.service.repository


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {


    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <S> executeCall(call: Call<S>, listener: APIListener<S>) {
        call.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFaliure(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                listener.onFaliure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }


    fun isConnectionAvaiable(): Boolean {
        var result = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNet = cm.activeNetwork ?: return false
        val netWorkCapabilities = cm.getNetworkCapabilities(activeNet) ?: return false

        result = when {
            netWorkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            netWorkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
        return result
    }
}
