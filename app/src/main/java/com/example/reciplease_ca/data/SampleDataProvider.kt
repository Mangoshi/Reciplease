package com.example.reciplease_ca.data

// This class was only used during testing, it would fill the database with 3 sample entries
class SampleDataProvider {

    companion object{
        // Defining placeholder variables
        private val sampleText1 = "A simple note"
        private val sampleText2 = "A note a\nline feed"
        private val sampleText3 = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris non urna a ex sodales hendrerit. Maecenas pulvinar eros nec purus ullamcorper maximus. Nunc sed congue odio, sit amet scelerisque augue. Fusce eros lectus, tincidunt nec mauris vel, sodales fringilla leo. Nullam at accumsan nisl. Donec ligula metus, convallis at rhoncus ut, ullamcorper et risus. Etiam quis mollis ex. Vestibulum suscipit magna quis massa dapibus, venenatis congue dolor interdum.
            Vestibulum feugiat molestie suscipit. Phasellus consectetur rutrum dignissim. Cras vehicula mi eu ex pretium, eget pharetra risus mollis. Suspendisse potenti. Suspendisse nec volutpat eros. Nam efficitur sem neque, nec posuere diam tincidunt at. Maecenas hendrerit dolor nec tempus tempor. Cras sit amet pharetra leo. 
        """.trimIndent()

        // Making an array list of meals based off the above data and hardcoded IDs
        fun getMeals() = arrayListOf(
            MealEntity(1, sampleText1),
            MealEntity(2, sampleText2),
            MealEntity(3, sampleText3)
        )
    }

}