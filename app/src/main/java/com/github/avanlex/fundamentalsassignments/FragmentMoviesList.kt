package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Movie
import com.github.avanlex.fundamentalsassignments.domain.MoviesDataSource
import com.google.android.material.snackbar.Snackbar

class FragmentMoviesList : Fragment() {
    private var recycler : RecyclerView ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_actor_list);
        recycler = recyclerView

        // RecyclerView ограничен и имеет фиксированный размер
        recyclerView.setHasFixedSize(true);

        // Здесь мы устанавливаем отступ
        recyclerView.addItemDecoration(MoviesListItemOffsetDecorator(24));

        // Установите табличный LayoutManager
        val layoutManager = GridLayoutManager(context, 2);
        recyclerView.layoutManager = layoutManager;

        // Инициализирование и установка адаптера в RecyclerView
        val moviesAdapter = MoviesRecyclerViewAdapter(clickListener);
        moviesAdapter.bindMovies(MoviesDataSource().getList())
        recyclerView.adapter = moviesAdapter;

        return view
    }

    private fun doOnClick(movie: Movie) {
        recycler?.let { rv ->
            Snackbar.make(
                rv,
                getString(R.string.chosen_item_name, movie.name),
                Snackbar.LENGTH_SHORT)
                .show()

            fragmentManager!!.beginTransaction()
                    .replace(R.id.main_activity, FragmentMoviesDetails.newInstance(movie))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private val clickListener = object : OnRecyclerMovieItemClicked {
        override fun onClick(movie: Movie) {
            doOnClick(movie)
        }
    }
}