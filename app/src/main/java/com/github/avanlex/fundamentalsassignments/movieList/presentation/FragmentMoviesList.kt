package com.github.avanlex.fundamentalsassignments.movieList.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.MovieApplication
import com.github.avanlex.fundamentalsassignments.R
import com.github.avanlex.fundamentalsassignments.movieDetails.presentation.FragmentMoviesDetails
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import kotlinx.serialization.ExperimentalSerializationApi

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

    private lateinit var viewModel: MoviesViewModel

    private lateinit var rvMovies : RecyclerView
    private lateinit var pbLoading : ProgressBar
    private lateinit var adapterMovies: MoviesRecyclerViewAdapter

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = (requireActivity().application as MovieApplication)
            .appContainer.getMoviesViewModel(this)
        initView(view)
        initMoviesRecyclerView()
        viewModel.loadMovies()
        viewModel.movieList.observe(this.viewLifecycleOwner, this.adapterMovies::bindMovies)
        viewModel.loadingState.observe(this.viewLifecycleOwner, this::setProgressVisibility)
        viewModel.addToFavorite.observe(this.viewLifecycleOwner, this.adapterMovies::notifyItemChanged)
    }

    private fun setProgressVisibility(state: Boolean) {
        pbLoading.visibility = if (state) ProgressBar.VISIBLE else ProgressBar.GONE
    }

    private fun initView(v: View) {
        rvMovies = v.findViewById(R.id.rv_movie_list)
        pbLoading = v.findViewById(R.id.pb_loading)
    }

    /**
     * Dynamic column count calculation for GridLayoutManager
     * @param scalingFactor - requires value from dimens.xml
     * the larger the value the less no. of columns will be calculated and vice versa
     * @return column count Int
     */
    private fun calculateColumnCount(scalingFactor: Float): Int {
        val dpWidth = resources.displayMetrics.widthPixels
        val count=  (dpWidth / scalingFactor).toInt()
        return if (count < 2) 2 else count
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
        adapterMovies.setAddToFavoriteClickListener{ movie, pos -> viewModel.addToFavorite(movie, pos) }
        rvMovies.adapter = adapterMovies
    }

    private fun openMovieDetails(movie: Movie) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity, FragmentMoviesDetails.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private val TAG = FragmentMoviesList::class.java.simpleName
        fun create() = FragmentMoviesList()
    }

}


