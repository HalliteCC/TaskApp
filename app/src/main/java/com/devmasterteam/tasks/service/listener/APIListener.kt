package com.devmasterteam.tasks.service.listener

interface APIListener<S> {

    fun onSuccess(result: S)
    fun onFaliure(message: String)
}