package com.example.reciplease_ca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reciplease_ca.api.RetrofitInstance
import com.example.reciplease_ca.models.Category
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    val categoriesList = MutableLiveData<List<Category>>()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getAllCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedCategories =
                RetrofitInstance.GET_ALL_CATEGORIES.getAllCategories().categories
            Log.i(TAG, "(getAllCategories) Fetched categories: $fetchedCategories")
            if(fetchedCategories.isNullOrEmpty()){
                Log.i(TAG, "(getAllCategories) Fetched categories is null or empty!")
                _isLoading.value = false
            }
            else{
                _categories.value = fetchedCategories
                _isLoading.value = false
            }
        }
    }
}