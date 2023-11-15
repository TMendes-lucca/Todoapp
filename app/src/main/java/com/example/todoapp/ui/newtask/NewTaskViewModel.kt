package com.example.todoapp.ui.newtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.service.model.TaskModel
import com.example.todoapp.service.repository.TaskRepository

class NewTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "Register new task"
    }
    val text: LiveData<String> = _text

    private val dbInstance = TaskRepository.getInstance(application)

    fun insertTask(task: TaskModel){
        dbInstance.insertTask(task)
    }
}