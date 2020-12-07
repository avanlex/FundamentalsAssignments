package com.github.avanlex.fundamentalsassignments


import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Movie


class  FragmentMoviesDetails : Fragment() {
    private var movie: Movie ?= null
    private var recycler : RecyclerView?= null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_movies_details, container, false)

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable("MOVIE")
        }

        v.findViewById<TextView>(R.id.tv_details_movie_name).text = movie?.name

        v.findViewById<TextView>(R.id.tv_details_pg).text = movie?.pg
        v.findViewById<TextView>(R.id.tv_details_storyline).text = movie?.storyline
        v.findViewById<VectorRatingBar>(R.id.vrb_details_rating).rating =
                movie!!.rating.toFloat()
        v.findViewById<TextView>(R.id.tv_details_review_count).text =
                getString(R.string.string_review_count, movie?.reviewCount)

        v.findViewById<TextView>(R.id.text_back).setOnClickListener {
                    fragmentManager?.popBackStack()
            }

        val recyclerView: RecyclerView = v.findViewById(R.id.rv_actor_list)
        recycler = recyclerView

        // Optimaze perfomance a little
        recyclerView.setHasFixedSize(true)

        // Offset between items workaround
        recyclerView.addItemDecoration(MoviesListItemOffsetDecorator(24))

        // Linear List
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager

        // Setting adapter to RecyclerView
        val moviesAdapter = ActorsRecyclerViewAdapter()
        movie?.let { moviesAdapter.bindActors(it.actors) }
        recyclerView.adapter = moviesAdapter

        // Get poster drawable by ID
        val poster: ImageView = v.findViewById(R.id.iv_details_poster) as ImageView
        val posterId = context!!.resources
            .getIdentifier(movie?.poster,"drawable", context!!.packageName)
        poster.setImageDrawable(context?.let { ContextCompat.getDrawable(it, posterId) })

        // Apply grayscale filter
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(matrix)
        poster.colorFilter = filter

        return v
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("MOVIE", movie)
        super.onSaveInstanceState(outState)
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails{
            val fragment = FragmentMoviesDetails()
            fragment.movie = movie
            return fragment
        }
    }
}