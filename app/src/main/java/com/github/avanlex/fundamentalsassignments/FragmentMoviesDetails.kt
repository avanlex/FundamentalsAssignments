package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Actor
import com.github.avanlex.fundamentalsassignments.data.models.Movie
import com.github.avanlex.fundamentalsassignments.domain.ActorsDataSource
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
/*
        val recyclerView : RecyclerView = view.findViewById(R.id.rv_movie_list);
        recycler = recyclerView

        // RecyclerView ограничен и имеет фиксированный размер
        recyclerView.setHasFixedSize(true);

        // Здесь мы устанавливаем отступ
        recyclerView.addItemDecoration(MoviesListItemOffsetDecorator(24));

        // Установите линейного LayoutManager
        val layoutManager = LinearLayoutManager(context);
        recyclerView.layoutManager = layoutManager;

        // Инициализирование и установка адаптера в RecyclerView
        val moviesAdapter = ActorsRecyclerViewAdapter(clickListener);
        moviesAdapter.bindMovies(ActorsDataSource().getList())
        recyclerView.adapter = moviesAdapter;

 */
        return view
    }
/*
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
*/
    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails{
            val fragment = FragmentMoviesDetails()
            fragment.movie = movie
            return fragment
        }
    }


}