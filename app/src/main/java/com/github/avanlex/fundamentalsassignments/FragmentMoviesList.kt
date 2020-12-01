package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.imageview.ShapeableImageView

class FragmentMoviesList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view?.findViewById<ShapeableImageView>(R.id.shImageMoviePoster)?.apply {
            setOnClickListener {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.main_activity, FragmentMoviesDetails())
                    ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
        return view
    }
}