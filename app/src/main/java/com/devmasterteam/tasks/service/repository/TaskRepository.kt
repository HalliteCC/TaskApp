package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository(val context: Context) : BaseRepository() {

    private val remote = RetrofitClient.getService(TaskService::class.java)


    fun list(listener: APIListener<List<TaskModel>>) {
        executeCall(remote.listTask(), listener, context)
    }

    fun list7DaysTask(listener: APIListener<List<TaskModel>>) {
        executeCall(remote.list7DaysTask(), listener, context)
    }

    fun listOverdue(listener: APIListener<List<TaskModel>>) {
        executeCall(remote.listOverdue(), listener, context)
    }

    fun create(task: TaskModel, listener: APIListener<Boolean>) {
        val call = remote.creatTask(task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listener, context)
    }

    fun update(task: TaskModel, listener: APIListener<Boolean>) {
        val call =
            remote.update(task.id, task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listener, context)
    }

    fun delete(id: Int, listener: APIListener<Boolean>) {
        executeCall(remote.deleteTask(id), listener, context)
    }

    fun complete(id: Int, listener: APIListener<Boolean>) {
        executeCall(remote.completeTask(id), listener, context)
    }

    fun undo(id: Int, listener: APIListener<Boolean>) {
        executeCall(remote.undo(id), listener, context)
    }

    fun load(id: Int, listener: APIListener<TaskModel>) {
        executeCall(remote.load(id), listener, context)
    }
}