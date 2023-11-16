package com.example.todoapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todoapp.service.model.TaskModel

class TaskListAdapter(private val tasks: List<TaskModel>) : Adapter<TaskListAdapter.TaskListViewHolder>(){

    class TaskListViewHolder(view : View) : ViewHolder(view) {
        
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}