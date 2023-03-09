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

class TaskRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(TaskService::class.java)


    fun list(listener: APIListener<List<TaskModel>>) {
       /* if(!isConnectionAvaiable()){
            listener.onFaliure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }*/
        executeCall(remote.listTask(), listener)
    }

    fun list7DaysTask(listener: APIListener<List<TaskModel>>) {
        executeCall(remote.list7DaysTask(), listener)
    }

    fun listOverdue(listener: APIListener<List<TaskModel>>) {
        executeCall(remote.listOverdue(), listener)
    }

    fun create(task: TaskModel, listener: APIListener<Boolean>) {
        val call = remote.creatTask(task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listener)
    }

    fun update(task: TaskModel, listener: APIListener<Boolean>) {
        val call =
            remote.update(task.id, task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listener)
    }

    fun delete(id: Int, listener: APIListener<Boolean>) {
        executeCall(remote.deleteTask(id), listener)
    }

    fun complete(id: Int, listener: APIListener<Boolean>) {
        executeCall(remote.completeTask(id), listener)
    }

    fun undo(id: Int, listener: APIListener<Boolean>) {
        executeCall(remote.undo(id), listener)
    }

    fun load(id: Int, listener: APIListener<TaskModel>) {
        executeCall(remote.load(id), listener)
    }
}