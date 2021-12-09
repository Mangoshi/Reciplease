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
        binding.mealCategory.text = meal.strCategory
        binding.mealName.text = meal.strMeal
        binding.mealArea.text = meal.strArea
        binding.mealInstructions.text = meal.strInstructions
        // ingredient 1
        if(meal.strIngredient1 != "") {
            binding.mealIngredient1.text = meal.strIngredient1
        } else { binding.mealIngredient1.text = " " }
        // ingredient 2
        if(meal.strIngredient2 != "") {
            binding.mealIngredient2.text = meal.strIngredient2
        } else { binding.mealIngredient2.text = " " }
        // ingredient 3
        if(meal.strIngredient3 != "") {
            binding.mealIngredient3.text = meal.strIngredient3
        } else { binding.mealIngredient3.text = " " }
        // ingredient 4
        if(meal.strIngredient4 != "") {
            binding.mealIngredient4.text = meal.strIngredient4
        } else { binding.mealIngredient4.text = " " }
        // ingredient 5
        if(meal.strIngredient5 != "") {
            binding.mealIngredient5.text = meal.strIngredient5
        } else { binding.mealIngredient5.text = " " }
        // ingredient 6
        if(meal.strIngredient6 != "") {
            binding.mealIngredient6.text = meal.strIngredient6
        } else { binding.mealIngredient6.text = " " }
        // ingredient 7
        if(meal.strIngredient7 != "") {
            binding.mealIngredient7.text = meal.strIngredient7
        } else { binding.mealIngredient7.text = " " }
        // ingredient 8
        if(meal.strIngredient8 != "") {
            binding.mealIngredient8.text = meal.strIngredient8
        } else { binding.mealIngredient8.text = " " }
        // ingredient 9
        if(meal.strIngredient9 != "") {
            binding.mealIngredient9.text = meal.strIngredient9
        } else { binding.mealIngredient9.text = " " }
        // ingredient 10
        if(meal.strIngredient10 != "") {
            binding.mealIngredient10.text = meal.strIngredient10
        } else { binding.mealIngredient10.text = " " }
        // ingredient 11
        if(meal.strIngredient11 != "") {
            binding.mealIngredient11.text = meal.strIngredient11
        } else { binding.mealIngredient11.text = " " }
        // ingredient 12
        if(meal.strIngredient12 != "") {
            binding.mealIngredient12.text = meal.strIngredient12
        } else { binding.mealIngredient12.text = " " }
        // ingredient 13
        if(meal.strIngredient13 != "") {
            binding.mealIngredient13.text = meal.strIngredient13
        } else { binding.mealIngredient13.text = " " }
        // ingredient 14
        if(meal.strIngredient14 != "") {
            binding.mealIngredient14.text = meal.strIngredient14
        } else { binding.mealIngredient14.text = " " }
        // ingredient 15
        if(meal.strIngredient15 != "") {
            binding.mealIngredient15.text = meal.strIngredient15
        } else { binding.mealIngredient15.text = " " }
        // ingredient 16
        if(meal.strIngredient16 != "") {
            binding.mealIngredient16.text = meal.strIngredient16
        } else { binding.mealIngredient16.text = " " }
        // ingredient 17
        if(meal.strIngredient17 != "") {
            binding.mealIngredient17.text = meal.strIngredient17
        } else { binding.mealIngredient17.text = " " }
        // ingredient 18
        if(meal.strIngredient18 != "") {
            binding.mealIngredient18.text = meal.strIngredient18
        } else { binding.mealIngredient18.text = " " }
        // ingredient 19
        if(meal.strIngredient19 != "") {
            binding.mealIngredient19.text = meal.strIngredient19
        } else { binding.mealIngredient19.text = " " }
        // ingredient 20
        if(meal.strIngredient20 != "") {
            binding.mealIngredient20.text = meal.strIngredient20
        } else { binding.mealIngredient20.text = " " }

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