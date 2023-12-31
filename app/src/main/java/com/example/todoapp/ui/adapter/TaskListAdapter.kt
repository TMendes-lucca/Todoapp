package com.example.todoapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.todoapp.R
import com.example.todoapp.databinding.TaskItemBinding
import com.example.todoapp.service.listener.TaskListener
import com.example.todoapp.service.model.TaskModel
import com.google.android.material.button.MaterialButton

class TaskListAdapter() : Adapter<TaskListAdapter.TaskListViewHolder>(){

    private var taskList: List<TaskModel> = arrayListOf()
    private lateinit var listener: TaskListener

    class TaskListViewHolder(view: View, listener: TaskListener) : ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.task_text)

        init {
            view.setOnClickListener{
                listener.onClickItem(view, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
       val view = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskListViewHolder(view.root, listener)
    }

    override fun getItemCount(): Int {
        return taskList.count()
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val id = taskList[position].id
        holder.description.text = taskList[position].description
        holder.itemView.findViewById<MaterialButton>(R.id.task_delete_button).setOnClickListener {
            listener.onClickDelete(it, id)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTasks(tasks: List<TaskModel>){
        taskList = tasks
        notifyDataSetChanged()
    }

    fun attachListener(listener: TaskListener){
        this.listener = listener
    }

}