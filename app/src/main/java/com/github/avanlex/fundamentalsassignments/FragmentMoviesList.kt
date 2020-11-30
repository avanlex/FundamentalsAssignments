package com.github.avanlex.fundamentalsassignments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView

class FragmentMoviesList : Fragment() {

    private var cardOnClickListener: CardOnClickListener? = null

    interface CardOnClickListener {
        fun onOpenDetailsClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CardOnClickListener)
            cardOnClickListener = context
    }

    override fun onDetach() {
        super.onDetach()
        cardOnClickListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?  {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view?.findViewById<ShapeableImageView>(R.id.shImageMoviePoster)?.apply {
            setOnClickListener {
                cardOnClickListener?.onOpenDetailsClicked()
            }
        }
        return view
    }
}