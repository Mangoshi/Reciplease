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
    private val _meals: MutableLiveData<List<Meal>> = MutableLiveData()
    val meals: LiveData<List<Meal>>
    get() = _meals

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
       getMeals()
    }

    fun getMeals() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedMeals = RetrofitInstance.api.getMeals()
            Log.i(TAG, "Fetched meals: $fetchedMeals")
            _meals.value = fetchedMeals.results
            _isLoading.value = false
        }
    }

//    val mealsList = MutableLiveData<List<Meal>>()


}