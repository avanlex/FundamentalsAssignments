package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() , FragmentMoviesList.CardOnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onOpenDetailsClicked(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity, FragmentMoviesDetails())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()
    }

}