package com.example.todoapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.service.model.TaskModel
import com.example.todoapp.service.repository.TaskRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository.getInstance(application.applicationContext)

    private val _text = MutableLiveData<String>().apply {
        value = "These are your tasks"
    }
    val text: LiveData<String> = _text

    private val _taskList = MutableLiveData<List<TaskModel>>()

    val taskList: LiveData<List<TaskModel>> = _taskList

     fun listAll(){
        _taskList.value = repository.readAllTasks()
    }
}