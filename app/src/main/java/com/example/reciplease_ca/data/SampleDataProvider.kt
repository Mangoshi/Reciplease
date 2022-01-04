package com.example.reciplease_ca.data

// This class was only used during testing, it would fill the database with 3 sample entries
class SampleDataProvider {

    companion object{
        // Defining placeholder variables
        private val sampleName1 = "Borgar"
        private val sampleName2 = "Chips"
        private val sampleName3 = "Spaghetti"

        // Making an array list of meals based off the above data and hardcoded IDs
        fun getMeals() = arrayListOf(
            MealEntity(1, sampleName1,),
            MealEntity(2, sampleName2),
            MealEntity(3, sampleName3)
        )
    }

}