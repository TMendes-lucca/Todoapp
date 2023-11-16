package com.example.todoapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.ui.adapter.TaskListAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var textView: TextView
    private lateinit var reciclerView: RecyclerView

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

        reciclerView.layoutManager = LinearLayoutManager(context)
        reciclerView.adapter = TaskListAdapter()

        // Method that lists all tasks when the fragment is created
        homeViewModel.listAll()

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
            // Update the UI
        }
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}