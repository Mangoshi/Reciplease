package com.example.reciplease_ca

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reciplease_ca.databinding.MainFragmentBinding

class MainFragment : Fragment(),
    MealsListAdapter.ListItemListener {


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: MealsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Now we have references to all the child view objects within the layout
        // Now we have a code block where we can reference the object multiple times
        with(binding.recyclerView) {
            // Height of each row will be the same regardless of contents
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            // Creating a visual divider between each of the rows
            addItemDecoration(divider)
        }

        viewModel.notesList.observe(viewLifecycleOwner, Observer {
            Log.i("noteLogging:", it.toString())
            adapter = MealsListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        })


        return binding.root
    }

    override fun onItemClick(noteId: Int) {
        Log.i(TAG, "onItemClick: received note id $noteId")
        val action = MainFragmentDirections.actionEditNote(noteId)
        findNavController().navigate(action)
    }
}