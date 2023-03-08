package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.*

interface TaskService {

    @GET("Task")
    fun listTask(): Call<List<TaskModel>>

    @GET("Task/Next7Days")
    fun list7DaysTask(): Call<List<TaskModel>>

    @GET("Task/Overdue")
    fun listOverdue(): Call<List<TaskModel>>

    @GET("task/{id}")
    fun load(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>

    @POST("Task")
    @FormUrlEncoded //Vai para o body
    fun creatTask(
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean,
    ): Call<Boolean>


    @PUT("Task")
    @FormUrlEncoded
    fun update(
        @Field("Id") id: Int,
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean
    ): Call<Boolean>

    @PUT("Task/Complete")
    @FormUrlEncoded
    fun completeTask(@Field("Id") id: Int): Call<Boolean>

    @PUT("Task/Undo")
    @FormUrlEncoded
    fun undo(@Field("Id") id: Int): Call<Boolean>



    @HTTP(method = "DELETE", path = "Task", hasBody = true)
    @FormUrlEncoded
    fun deleteTask(@Field("Id") id: Int): Call<Boolean>
}


