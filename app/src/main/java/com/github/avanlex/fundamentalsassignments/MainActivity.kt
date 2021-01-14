package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.avanlex.fundamentalsassignments.movieList.presentation.FragmentMoviesList

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_activity, FragmentMoviesList.create())
                    .commitNow()
        }
    }

}