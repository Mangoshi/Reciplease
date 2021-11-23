package com.example.reciplease_ca.data

class SampleDataProvider {

    companion object{
        private val sampleName1 = "Omelette"
        private val sampleName2 = "Pizza"
        private val sampleName3 = "Spaghetti"

        private val sampleCategory1 = "Vegetarian"
        private val sampleCategory2 = "Vegan"
        private val sampleCategory3 = "Meat"

        private val sampleInstructions1 = "Bacon ipsum dolor amet ground round pork loin capicola buffalo tenderloin fatback flank."
        private val sampleInstructions2 = "Short loin spare ribs cow doner pork belly strip steak andouille drumstick."
        private val sampleInstructions3 = "Capicola shankle boudin, landjaeger kevin cupim flank corned beef sausage short loin ground round venison jerky brisket ribeye. Drumstick ground round shoulder tongue, buffalo strip steak hamburger capicola. Leberkas rump buffalo, capicola turkey pork chop doner landjaeger alcatra beef pancetta sausage. Chicken biltong jerky kielbasa, ground round flank swine burgdoggen boudin sausage pastrami bacon shoulder landjaeger t-bone."



        fun getMeals() = arrayListOf(
            MealEntity(1, sampleName1, sampleCategory1, sampleInstructions1),
            MealEntity(2, sampleName2, sampleCategory2, sampleInstructions2),
            MealEntity(3, sampleName3, sampleCategory3, sampleInstructions3)
        )
    }

}