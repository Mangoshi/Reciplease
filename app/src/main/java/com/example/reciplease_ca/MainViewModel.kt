package com.example.reciplease_ca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciplease_ca.api.RetrofitInstance
import com.example.reciplease_ca.data.MealEntity
import com.example.reciplease_ca.data.SampleDataProvider
import com.example.reciplease_ca.models.Post
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _meals: MutableLiveData<List<Post>> = MutableLiveData()
    val meals: LiveData<List<Post>>
        get() = _meals

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val notesList = MutableLiveData<List<MealEntity>>()

    init {
        notesList.value = SampleDataProvider.getMeals()
    }

    fun getMeals() {
        viewModelScope.launch {
            val fetchedMeals = RetrofitInstance.api.getMeals()
            Log.i(TAG, "Fetched meals: $fetchedMeals")
            _meals.value = fetchedMeals
        }
    }
}