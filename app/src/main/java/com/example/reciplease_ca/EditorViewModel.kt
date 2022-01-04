package com.example.reciplease_ca

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.reciplease_ca.data.AppDatabase
import com.example.reciplease_ca.data.MealEntity
import com.example.reciplease_ca.models.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// EditorViewModel contains all variables & functions needed on the editor fragment
class EditorViewModel (app: Application) : AndroidViewModel(app) {

    // Initialising database variable using an instance of the global application state
    private val database = AppDatabase.getInstance(app)

    // Initialising MutableLiveData variables used for monitoring the state of hearts
    private val _currentHeart: MutableLiveData<MealEntity?> = MutableLiveData()
    val currentHeart : MutableLiveData<MealEntity?>
        get() = _currentHeart

    // MutableLiveData used to monitor the state of meal details
    private val _meals: MutableLiveData<List<Meal>> = MutableLiveData()
    val meals: LiveData<List<Meal>>
        get() = _meals

    // Function used to save meal to heart list
    fun saveHeart(mealEntity: MealEntity) {
        // Launching coroutine scope
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                // Executing insertMeal from mealDao
                database?.mealDao()?.insertMeal(mealEntity)
                // Logging to logcat
                Log.i("Heart", "Saved Heart!")
            }
        }
    }

    // Function used to remove meal from heart list
    fun removeHeart(mealId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                // Executing removeMeal from mealDao
                database?.mealDao()?.removeMeal(mealId)
                // Posting value to _currentHeart so I can access the value outside of this scope
                currentHeart.postValue(null)
                Log.i("Heart", "Removed Heart!")
            }
        }
    }

    // Function used to retrieve meal from heart list
    fun getHeart(mealId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // Executing getMealById from mealDao
                val heart = database?.mealDao()?.getMealById(mealId)
                heart?.let {
                    currentHeart.postValue(it)
                    Log.i("Heart", "Got heart:" + it.text)
                }
            }
        }
    }
}