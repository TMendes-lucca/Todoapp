package com.example.todoapp.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.todoapp.service.model.TaskModel

class TaskRepository private constructor(context: Context) {


    private val taskDataBase = TaskDataBase(context)

    //Singleton
    companion object {
        private lateinit var repository: TaskRepository

        fun getInstance(context: Context): TaskRepository {
            if (!Companion::repository.isInitialized) {
                repository = TaskRepository(context)
            }
            return repository
        }
    }

    fun insertTask(task: TaskModel): Boolean {
        return try {
            val dbW = taskDataBase.writableDatabase
            val values = ContentValues()

            values.put("description", task.description)

            dbW.insert("tasks", null, values)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun readAllTasks(): List<TaskModel> {
        val list = mutableListOf<TaskModel>()
        val db = taskDataBase.readableDatabase
        try {
            val selection = arrayOf("id", "description")
            val cursor = db.query("tasks", selection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val description = cursor.getString(cursor.getColumnIndex("description"))

                    val task = TaskModel(id, description)
                    list.add(task)

                }
            }
            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }
}