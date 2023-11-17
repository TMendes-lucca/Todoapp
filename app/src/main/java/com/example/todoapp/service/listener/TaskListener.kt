package com.example.todoapp.service.listener

import android.view.View

interface TaskListener {
    fun onClickItem(view: View, index: Int)

    fun onClickDelete(index: Int)
}