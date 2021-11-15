package com.example.reciplease_ca

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reciplease_ca.data.NoteEntity
import com.example.reciplease_ca.data.SampleDataProvider

class MainViewModel : ViewModel() {

    val notesList = MutableLiveData<List<NoteEntity>>()

    init {
        notesList.value = SampleDataProvider.getNotes()
    }
}