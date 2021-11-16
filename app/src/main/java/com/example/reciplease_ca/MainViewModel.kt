package com.example.reciplease_ca

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciplease_ca.data.MealEntity
import com.example.reciplease_ca.data.SampleDataProvider

class MainViewModel : ViewModel() {

    val notesList = MutableLiveData<List<MealEntity>>()

    init {
        notesList.value = SampleDataProvider.getNotes()
    }
}