package com.example.reciplease_ca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.reciplease_ca.databinding.EditorFragmentBinding
import com.example.reciplease_ca.models.Meal

class EditorFragment : Fragment() {
    private lateinit var viewModel: EditorViewModel
    private lateinit var binding: EditorFragmentBinding
    private lateinit var meal: Meal
    private val args: EditorFragmentArgs by this.navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_meal)
        }
        setHasOptionsMenu(false)

        binding = EditorFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
//        viewModel.getMealByName(args.mealName)
//
//        binding.mealName.text = args.mealName
//        binding.mealCategory.text = args.mealCategory
//        binding.mealInstructions.text = args.mealInstructions

        val bundle = this.arguments
        if (bundle != null) {
            meal = bundle.getParcelable<Meal>("meal")!!
        }

        binding.mealName.text = meal.strMeal
        binding.mealCategory.text = meal.strCategory
        binding.mealInstructions.text = meal.strInstructions

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAndReturn(): Boolean {
        // Go back to the main fragment
        findNavController().navigateUp()
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)

    }

}