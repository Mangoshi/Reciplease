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
import com.example.reciplease_ca.models.Meal

class MainFragment : Fragment(),
    MealListAdaptor.ListItemListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: MealListAdaptor
    private val listOfFilms = mutableListOf<Meal>()

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

        viewModel.meals.observe(this, Observer { meals ->
            Log.i(TAG, "Number of meals: ${meals.size}")
            listOfFilms.addAll(meals)
            adapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            Log.i(TAG, "isLoading $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })


        return binding.root
    }

    override fun onItemClick(mealId: Int) {
        Log.i(TAG, "onItemClick: received meal id $mealId")
        val action = MainFragmentDirections.actionEditNote(mealId)
        findNavController().navigate(action)
    }
}