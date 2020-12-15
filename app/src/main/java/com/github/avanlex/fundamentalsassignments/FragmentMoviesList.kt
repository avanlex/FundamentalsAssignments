package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.Movie
import com.github.avanlex.fundamentalsassignments.data.loadMovies
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment() {
    private lateinit var rvMovies : RecyclerView
    private lateinit var adapterMovies: MoviesRecyclerViewAdapter
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi(view)
        initMoviesRecyclerView()
        loadMoviesData()
    }

    override fun onDestroy() {
        scope.cancel("Fragment destroys")
        super.onDestroy()
    }

    private fun initUi(v: View) {
        rvMovies = v.findViewById(R.id.rv_movie_list)
    }

    private fun loadMoviesData() {
        scope.launch {
            val movieList = loadMovies(requireContext())
            withContext(Dispatchers.Main) {
                adapterMovies.bindMovies(movieList)
            }
        }
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
        adapterMovies.setOnOpenMovieDetailsClickListener{ movieItem -> openMovieDetails(movieItem)}
        adapterMovies.setAddToFavoriteClickListener{movie, pos -> addToFavorite(movie, pos)}
        rvMovies.adapter = adapterMovies
    }

    private fun openMovieDetails(movie: Movie) {
        fragmentManager!!.beginTransaction()
            .replace(R.id.main_activity, FragmentMoviesDetails.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }

    private fun addToFavorite(movie: Movie, position: Int) {
        movie.favorite = ! movie.favorite
        adapterMovies.notifyItemChanged(position)
    }
}