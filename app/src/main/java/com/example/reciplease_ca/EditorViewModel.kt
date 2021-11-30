package com.example.reciplease_ca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciplease_ca.api.RetrofitInstance
import com.example.reciplease_ca.models.Meal
import kotlinx.coroutines.launch

class EditorViewModel : ViewModel() {
    private val _meals: MutableLiveData<List<Meal>> = MutableLiveData()
    val meals: LiveData<List<Meal>>
        get() = _meals

    val mealsList = MutableLiveData<List<Meal>>()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getMealByName(mealName: String){
        // a coroutine function can only be called from a coroutine,
        // so we make one:
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedMeals = RetrofitInstance.GET_MEAL_BY_NAME.getMealByName(mealName).meals
            Log.i(TAG, "(getMealByName) Fetched meals: $fetchedMeals")
            if(fetchedMeals.isNullOrEmpty()){
                Log.i(TAG, "(getMealByName) Fetched meals is null or empty!")
                _isLoading.value = false
            }
            else{
                _meals.value = fetchedMeals
                _isLoading.value = false
            }
        }
    }

    fun getMealById(mealId: Int){
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedMeals = RetrofitInstance.GET_MEAL_BY_ID.getMealById(mealId).meals
            Log.i(TAG, "(getMealById) Fetched meals: $fetchedMeals")
            if(fetchedMeals.isNullOrEmpty()){
                Log.i(TAG, "(getMealById) Fetched meals is null or empty!")
                _isLoading.value = false
            }
            else{
                _meals.value = fetchedMeals
                _isLoading.value = false
            }
        }
    }
}