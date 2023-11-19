package com.example.todoapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.service.listener.TaskListener
import com.example.todoapp.ui.adapter.TaskListAdapter
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var textView: TextView
    private lateinit var reciclerView: RecyclerView
    private val adapter: TaskListAdapter = TaskListAdapter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Define values to lateinit variables
        textView = binding.textHome
        reciclerView = binding.homeRvTaskList

        // Set the RecyclerView needed definitions
        reciclerView.layoutManager = LinearLayoutManager(context)
        reciclerView.adapter = adapter

        val listener = object : TaskListener {
            override fun onClickItem(view: View, index: Int) {
                val radioBtn = view.findViewById<MaterialCheckBox>(R.id.task_radio_button)
                radioBtn.isChecked = !radioBtn.isChecked
            }

            override fun onClickDelete(view: View, index: Int) {
                MaterialAlertDialogBuilder(view.context)
                    .setTitle("Delete task")
                    .setMessage("Would you like to delete this task?")
                    .setNegativeButton("Cancel") { dialog, which ->
                        Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show()

                    }
                    .setPositiveButton("Delete") { dialog, which ->
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                        homeViewModel.deleteTask(index)
                        homeViewModel.listAll()
                    }
                    .show()
            }
        }

        adapter.attachListener(listener)

        // Method that lists all tasks when the fragment is created
        homeViewModel.listAll()


        Toast.makeText(context, "onCreateView", Toast.LENGTH_SHORT).show()
        //Set the observers from the ViewModel
        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        homeViewModel.taskList.observe(viewLifecycleOwner) {
            adapter.updateTasks(it)
        }
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

}