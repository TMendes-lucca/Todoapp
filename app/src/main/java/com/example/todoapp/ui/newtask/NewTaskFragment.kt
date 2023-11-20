package com.example.todoapp.ui.newtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.FragmentNewTaskBinding
import com.example.todoapp.service.model.TaskModel

class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newTaskViewModel =
            ViewModelProvider(this)[NewTaskViewModel::class.java]
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textNewTask
        newTaskViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.buttonNewTask.setOnClickListener {
            val taskText = binding.editTextNewTask
            val taskModel = TaskModel(0, taskText.editText?.text.toString())

            if (taskText.editText?.text.toString().isNotBlank()) {
                // Clear the form, insert into DB and send Toast to user
                taskText.editText?.text?.clear()
                newTaskViewModel.insertTask(taskModel)
                Toast.makeText(context, "Task created", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Task description is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}