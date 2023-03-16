package com.devmasterteam.tasks.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.SecurityPreferences
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import kotlin.coroutines.coroutineContext

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository = PersonRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _register = MutableLiveData<ValidationModel>()
    val register: LiveData<ValidationModel> = _register

    fun create(name: String, email: String, password: String) {
        personRepository.create(name, email, password, object : APIListener<PersonModel>{
            override fun onSuccess(result: PersonModel) {
                securityPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
                securityPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
                securityPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)

                RetrofitClient.addHeaders(result.token, result.personKey)

                _register.value = ValidationModel()

            }

            override fun onFaliure(message: String) {
                _register.value = ValidationModel(message)
            }

        })
    }

}