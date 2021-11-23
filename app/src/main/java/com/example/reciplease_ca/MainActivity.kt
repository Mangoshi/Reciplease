package com.example.reciplease_ca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reciplease_ca.databinding.ActivityMainBinding
import com.example.reciplease_ca.models.Post

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mealsListAdapter: MealsListAdapter
    private val mealEntries = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.meals.observe(this, Observer { meals =>
            Log.i(TAG, "Number of meals: ${mealsList.size}")
            mealEntries.addAll(mealEntries)
            mealsListAdapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, Observer { isLoading =>
            Log.i(TAG, "isLoading $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.DONE
        })

        mealsListAdapter = MealsListAdapter(this, mealEntries)
        binding.rvPosts.adapter = LinearLayoutManager(this)

        binding.button.setOnClickListener {
            viewModel.getPosts()
        }
    }
}