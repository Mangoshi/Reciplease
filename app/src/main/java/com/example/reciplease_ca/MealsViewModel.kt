package com.example.reciplease_ca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciplease_ca.api.RetrofitInstance
import com.example.reciplease_ca.models.Meal
import kotlinx.coroutines.launch

class MealsViewModel : ViewModel() {
    private val _meals: MutableLiveData<List<Meal>> = MutableLiveData()
    val meals: LiveData<List<Meal>>
        get() = _meals

    val mealsList = MutableLiveData<List<Meal>>()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _selectedMeal: MutableLiveData<List<Meal>> = MutableLiveData()
    val selectedMeal: MutableLiveData<List<Meal>>
        get() = _selectedMeal

    // getMealsByCategory to get all meals under a certain category.
    // to be displayed in MealsFragment
    fun getMealsByCategory(categoryName: String){
        // a coroutine function can only be called from a coroutine,
        // so we make one:
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedMeals = RetrofitInstance.GET_MEALS_BY_CATEGORY.getMealsByCategory(categoryName).meals
            Log.i(TAG, "(getMealsByCategory) Fetched meals: $fetchedMeals")
            if(fetchedMeals.isNullOrEmpty()){
                Log.i(TAG, "(getMealsByCategory) Fetched meals is null or empty!")
                _isLoading.value = false
            }
            else{
                _meals.value = fetchedMeals
                _isLoading.value = false
            }
        }
    }

    // getMealByName to get the details of a named meal
    // to be displayed in EditorFragment
    fun getMealByName(mealName: String){
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedMeal = RetrofitInstance.GET_MEAL_BY_NAME.getMealByName(mealName).meals
            Log.i(TAG, "(getMealByName) Fetched meal: $fetchedMeal")
            _selectedMeal.value = fetchedMeal
            _isLoading.value = false
        }
    }
}