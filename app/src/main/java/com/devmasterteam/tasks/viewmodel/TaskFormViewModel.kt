package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.TaskRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {

    private val priorityRepository = PriorityRepository(application.applicationContext)

    private val _priorityList = MutableLiveData<List<PriorityModel>>()
    val priorityList: LiveData <List<PriorityModel>> = _priorityList

    fun loadPriorities(){
       _priorityList.value = priorityRepository.list()
    }

}