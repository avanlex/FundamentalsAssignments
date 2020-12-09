package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Movie
import com.github.avanlex.fundamentalsassignments.domain.MoviesDataSource
import kotlin.math.roundToInt


class FragmentMoviesList : Fragment() {
    private lateinit var rvMovies : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        initUi(view)
        initMoviesRecyclerView()
        return view
    }

    private fun initUi(v: View) {
        rvMovies = v.findViewById(R.id.rv_movie_list)!!
    }

    private fun initMoviesRecyclerView() {
        // Optimaze perfomance a little
        rvMovies.setHasFixedSize(true)

        // Offset between items workaround
        rvMovies.addItemDecoration(MoviesListItemOffsetDecorator(R.dimen.movie_item_spacing))

        val columns = 2
        val layoutManager = GridLayoutManager(context, columns)
        rvMovies.layoutManager = layoutManager

        // Setting adapter to RecyclerView
        val adapterMovies = MoviesRecyclerViewAdapter(object : OnRecyclerMovieItemClicked {
            override fun onClick(movie: Movie) {
                openMovieDetails(movie)
            }
        })
        adapterMovies.bindMovies(MoviesDataSource().getList())
        rvMovies.adapter = adapterMovies
    }

    private fun openMovieDetails(movie: Movie) {
        rvMovies.let {
            fragmentManager!!.beginTransaction()
                .replace(R.id.main_activity, FragmentMoviesDetails.newInstance(movie))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()
        }
    }
}