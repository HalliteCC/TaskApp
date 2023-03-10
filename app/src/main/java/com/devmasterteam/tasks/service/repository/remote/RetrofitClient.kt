package com.devmasterteam.tasks.service.repository.remote


import com.devmasterteam.tasks.service.constants.TaskConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient private constructor(){

    companion object {

        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "http://devmasterteam.com/CursoAndroidAPI/"

        private  var token: String = ""
        private  var personKey: String = ""

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(TaskConstants.HEADER.TOKEN_KEY, token)
                    .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                    .build()
                chain.proceed(request)
            }

            if(!::INSTANCE.isInitialized){
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
            return INSTANCE
        }

        fun <S> getService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }

        fun addHeaders (tokenValue: String, personKeyValue: String){
            token = tokenValue
            personKey = personKeyValue
        }
    }
}