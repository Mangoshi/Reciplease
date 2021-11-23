package com.example.reciplease_ca

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reciplease_ca.databinding.ActivityMainBinding
import com.example.reciplease_ca.models.Meal

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mealAdaptor: MealAdaptor
    private val listOfMeals = mutableListOf<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.meals.observe(this, Observer { meals ->
            Log.i(TAG, "Number of meals: ${meals.size}")
            listOfMeals.addAll(meals)
            mealAdaptor.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, Observer { isLoading ->
            Log.i(TAG, "isLoading $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.DONE
        })
        mealAdaptor = MealAdaptor(this, meals)
        binding.rvPosts.adapter = mealAdaptor
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.button.setOnClickListener {
            viewModel.getMeals()
        }
    }
}