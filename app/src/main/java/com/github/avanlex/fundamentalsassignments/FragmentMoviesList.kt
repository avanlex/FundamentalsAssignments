package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.Movie
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private val viewModel: MovieListViewModel by viewModels { MovieListViewModelFactory() }

    private lateinit var rvMovies : RecyclerView
    private lateinit var adapterMovies: MoviesRecyclerViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        initMoviesRecyclerView()
        viewModel.loadMovies()
        viewModel.movieList.observe(this.viewLifecycleOwner, this::updateAdapter)
    }

    private fun updateAdapter(movieList: List<Movie>) {
        adapterMovies.bindMovies(movieList)
    }

    private fun initView(v: View) {
        rvMovies = v.findViewById(R.id.rv_movie_list)
    }

    /**
     * Dynamic column count calculation for GridLayoutManager
     * @param scalingFactor - requires value from dimens.xml
     * the larger the value the less no. of columns will be calculated and vice versa
     * @return column count Int
     */
    private fun calculateColumnCount(scalingFactor : Float): Int {
        val dpWidth = resources.displayMetrics.widthPixels
        return (dpWidth / scalingFactor).toInt()
    }

    private fun initMoviesRecyclerView() {
        // Optimize performance a little
        rvMovies.setHasFixedSize(true)

        // Offset between items workaround
        val offset = resources.getDimension(R.dimen.movie_item_spacing).toInt()
        rvMovies.addItemDecoration(MoviesListItemOffsetDecorator(offset))

        val columns = calculateColumnCount(resources.getDimension(R.dimen.span_scaling_factor))
        val layoutManager = GridLayoutManager(context, columns)
        rvMovies.layoutManager = layoutManager

        adapterMovies = MoviesRecyclerViewAdapter()
        adapterMovies.setOnOpenMovieDetailsClickListener{ movieItem -> openMovieDetails(movieItem) }
        adapterMovies.setAddToFavoriteClickListener{ movie, pos -> addToFavorite(movie, pos) }
        rvMovies.adapter = adapterMovies
    }

    private fun openMovieDetails(movie: Movie) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity, FragmentMoviesDetails.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }

    private fun addToFavorite(movie: Movie, position: Int) {
        movie.favorite = ! movie.favorite
        adapterMovies.notifyItemChanged(position)
    }
}


