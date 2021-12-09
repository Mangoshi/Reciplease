package com.example.reciplease_ca

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciplease_ca.models.Meal

// EditorViewModel contains all variables & functions needed on the editor fragment
class EditorViewModel : ViewModel() {
    private val _meals: MutableLiveData<List<Meal>> = MutableLiveData()
    val meals: LiveData<List<Meal>>
        get() = _meals
}