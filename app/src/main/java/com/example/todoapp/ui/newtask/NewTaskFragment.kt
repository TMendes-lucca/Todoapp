package com.example.todoapp.ui.newtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.FragmentNewTaskBinding
import com.example.todoapp.service.model.TaskModel

class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newTaskViewModel =
            ViewModelProvider(this)[NewTaskViewModel::class.java]

        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNewTask
        newTaskViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.buttonNewTask.setOnClickListener {
            // TODO - Clear the form
            val taskText = binding.editTextNewTask.editText?.text.toString()

            val taskModel = TaskModel(0, taskText)

            newTaskViewModel.insertTask(taskModel)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}