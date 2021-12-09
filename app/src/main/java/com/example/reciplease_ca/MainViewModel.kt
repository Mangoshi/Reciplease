package com.example.reciplease_ca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciplease_ca.api.RetrofitInstance
import com.example.reciplease_ca.models.Category
import kotlinx.coroutines.launch

// Defining what will be made available for main fragment
class MainViewModel : ViewModel() {

    // Mutable live data is used to monitor live data
    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    val categoriesList = MutableLiveData<List<Category>>()

    // Could use this if I wanted a loader animation
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // getAllCategories function for retrieving all the categories
    fun getAllCategories() {
        viewModelScope.launch {
            // set isLoading to true while the data is retrieved
            _isLoading.value = true
            // assign fetchedCategories the value of the response from the Retrofit instance
            val fetchedCategories =
                RetrofitInstance.GET_ALL_CATEGORIES.getAllCategories().categories
            // Log the fetchedCategories
            Log.i(TAG, "(getAllCategories) Fetched categories: $fetchedCategories")
            // If null or empty, log and set isLoading to false
            if(fetchedCategories.isNullOrEmpty()){
                Log.i(TAG, "(getAllCategories) Fetched categories is null or empty!")
                _isLoading.value = false
            }
            // Else if success, assign _categories to the value of fetchedCategories
            // and set _isLoading to false
            else{
                _categories.value = fetchedCategories
                _isLoading.value = false
            }
        }
    }
}