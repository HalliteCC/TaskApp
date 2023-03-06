package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService

class TaskRepository(val context: Context) {

    private val remote = RetrofitClient.getService(TaskService::class.java)
}