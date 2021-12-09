package com.example.reciplease_ca

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reciplease_ca.databinding.MealsFragmentBinding
import com.example.reciplease_ca.models.Meal

class MealsFragment : Fragment(),
    MealsListAdapter.ListItemListener {

    private lateinit var viewModel: MealsViewModel
    private lateinit var binding: MealsFragmentBinding
    private lateinit var adapter: MealsListAdapter
    private var selectedMeal: Meal? = null
    private val args: MealsFragmentArgs by this.navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_meal)
        }
        setHasOptionsMenu(false)

        binding = MealsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MealsViewModel::class.java)
        viewModel.getMealsByCategory(args.categoryName)

        with(binding.mealsRecyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }
        viewModel.meals.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, it.toString())
            adapter = MealsListAdapter(it, this@MealsFragment)
            binding.mealsRecyclerView.adapter = adapter
            binding.mealsRecyclerView.layoutManager = LinearLayoutManager(activity)
        })
        return binding.root
    }

    override fun onItemClick(
        mealId: Int,
        mealName: String
    ) {
        Log.i(TAG, "(mealsFragment) onItemClick: received meal with id: $mealId and name: $mealName")
        // On click run getMealByName to make the third API call to retrieve meals data
        viewModel.getMealByName(mealName)

        // Observer for selected meal so the changes can be monitored
        viewModel.selectedMeal.observe(viewLifecycleOwner, Observer{
            with(it){
                selectedMeal = it.first()
            }
        })

        // Navigate to EditorFragment, passing selectedMeal as an argument
        val direction = selectedMeal?.let { MealsFragmentDirections.actionMealsToEditor(selectedMeal!!) }
        // Error handling
        if (direction != null) {
            findNavController().navigate(direction)
            selectedMeal = null
        }
    }

}