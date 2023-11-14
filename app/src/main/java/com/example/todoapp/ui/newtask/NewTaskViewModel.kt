package com.example.todoapp.ui.newtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewTaskViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Register new task"
    }
    val text: LiveData<String> = _text
}