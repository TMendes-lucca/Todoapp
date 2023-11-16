package com.example.todoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todoapp.R
import com.example.todoapp.databinding.TaskItemBinding
import com.example.todoapp.service.model.TaskModel

class TaskListAdapter() : Adapter<TaskListAdapter.TaskListViewHolder>(){

    private var taskList: List<TaskModel> = arrayListOf()

    class TaskListViewHolder(view: View) : ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.task_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
       val view = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskListViewHolder(view.root)
    }

    override fun getItemCount(): Int {
        return taskList.count()
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.description.text = taskList[position].description
    }

    fun updateTasks(tasks: List<TaskModel>){
        taskList = tasks
    }

}