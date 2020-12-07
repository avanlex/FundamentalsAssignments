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
import com.github.avanlex.fundamentalsassignments.data.models.Actor
import com.github.avanlex.fundamentalsassignments.data.models.Movie
import com.google.android.material.snackbar.Snackbar


class  FragmentMoviesDetails : Fragment() {
    private var movie: Movie ?= null
    private var recycler : RecyclerView?= null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view.findViewById<TextView>(R.id.tv_details_movie_name).text = movie?.name ?: "undefined"
        view.findViewById<TextView>(R.id.tv_details_pg).text = movie?.pg ?: "undefined"
        view.findViewById<TextView>(R.id.tv_details_storyline).text = movie?.storyline ?: "undefined"
        view.findViewById<VectorRatingBar>(R.id.vrb_details_rating).rating =
                movie?.rating?.toFloat() ?: 0.0.toFloat()
        view.findViewById<TextView>(R.id.tv_details_review_count).text = getString(R.string.string_review_count, movie?.reviewCount)

        view.findViewById<TextView>(R.id.text_back)
            .setOnClickListener {
                fragmentManager?.popBackStack()
            }

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_actor_list);
        recycler = recyclerView

        // RecyclerView ограничен и имеет фиксированный размер
        recyclerView.setHasFixedSize(true);

        // Здесь мы устанавливаем отступ
        recyclerView.addItemDecoration(MoviesListItemOffsetDecorator(24));

        // Установите линейного LayoutManager
        val layoutManager = LinearLayoutManager(context);
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = layoutManager;

        // Инициализирование и установка адаптера в RecyclerView
        val moviesAdapter = ActorsRecyclerViewAdapter(clickListener);
        movie?.let { moviesAdapter.bindActors(it.actors) }
        recyclerView.adapter = moviesAdapter;

        val poster: ImageView = view.findViewById(R.id.iv_details_poster) as ImageView
        val posterId = context!!.resources.getIdentifier(movie?.poster,
                "drawable",
                context!!.packageName)
        poster.setImageDrawable(context?.let { ContextCompat.getDrawable(it, posterId) })

        // Apply grayscale filter
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(matrix)
        poster.colorFilter = filter
        return view
    }

    private fun doOnClick(actor: Actor) {
        recycler?.let { rvMoviesList ->
            Snackbar.make(
                    rvMoviesList,
                    getString(R.string.chosen_item_name, actor.name),
                    Snackbar.LENGTH_SHORT)
                    .show()
        }
    }

    private val clickListener = object : OnRecyclerActorItemClicked {
        override fun onClick(actor: Actor) {
            doOnClick(actor)
        }
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails{
            val fragment = FragmentMoviesDetails()
            fragment.movie = movie
            return fragment
        }
    }
}