package com.example.reciplease_ca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciplease_ca.api.RetrofitInstance
import com.example.reciplease_ca.models.Meal
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

//    init {
//        mealsList.value = SampleDataProvider.getMeals()
//    }

    val _meals: MutableLiveData<List<Meal>> = MutableLiveData()
    val meals: LiveData<List<Meal>>
        get() = _meals

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val mealsList = MutableLiveData<List<Meal>>()

    fun getMeals(searchQuery: String){
        // a coroutine function can only be called from a coroutine,
        // so we make one:
        viewModelScope.launch {
            val fetchedMeals = RetrofitInstance.api.getMeals(searchQuery).meals
            Log.i(TAG, "Fetched meals: $fetchedMeals")
            _meals.value = fetchedMeals
            _isLoading.value = false
        }
    }
}