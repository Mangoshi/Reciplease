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
import com.bumptech.glide.Glide
import com.example.reciplease_ca.databinding.EditorFragmentBinding
import com.example.reciplease_ca.models.Meal

// EditorFragment class using Fragment constructor
class EditorFragment : Fragment() {
    // Defining the common late-initialised variables
    // lateinit allows initializing a not-null property outside of a constructor
    // viewModel is assigned the value of the editor's view model
    private lateinit var viewModel: EditorViewModel
    // binding is assigned the value of EditorFragmentBinding from editor_fragment.xml
    private lateinit var binding: EditorFragmentBinding
    // meal is assigned the value of the Meal data class
    private lateinit var meal: Meal

    // onCreateView is called to have the fragment instantiate its user interface view.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { super.onCreateView(inflater, container, savedInstanceState)

        // activity as AppCompatActivity with ActionBar support
        // AppCompatActivity is a base class for activities that use the support library action bar features.
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        // ActionBar preferences:
        // enable home button in the corner
        // enable application home affordance in the action bar.
        // enable "home" returning up by a single level in UI rather than all the way
        // set ic_meal as the home indicator icon
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_meal)
        }
        // enable menu
        // without this the home button wouldn't work
        setHasOptionsMenu(true)

        binding = EditorFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)

//        viewModel.getMealByName(args.mealName)
//        binding.mealName.text = args.mealName
//        binding.mealCategory.text = args.mealCategory
//        binding.mealInstructions.text = args.mealInstructions

        val bundle = this.arguments
        if (bundle != null) {
            // Un-bundles the the parceled meal from the nav arguments
            meal = bundle.getParcelable("meal")!!
        }

        // binding meal details to the TextViews on editor fragment layout
        binding.mealName.text = meal.strMeal
        binding.mealCategory.text = meal.strCategory
        binding.mealInstructions.text = meal.strInstructions

        // using Glide to set the mealImage
        Glide.with(binding.root)
            .load(meal.strMealThumb)
            .into(binding.mealImage)

        // For handling what happens when the user presses back
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        // Return the root of the binding
        return binding.root
    }

    // when options menu is selected (home), saveAndReturn
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    // saveAndReturn navigates back up one fragment in the graph
    private fun saveAndReturn(): Boolean {
        // Go back to the main fragment
        findNavController().navigateUp()
        return true
    }

    // When the activity is created, set the viewModel provider
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
    }

}