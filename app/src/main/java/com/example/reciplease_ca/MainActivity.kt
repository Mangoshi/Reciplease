package com.example.reciplease_ca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// MainActivity is only used to set the view to activity_main.xml
// Which then defines the FragmentContainer that houses MainFragment and so on
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}