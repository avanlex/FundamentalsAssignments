package com.github.avanlex.fundamentalsassignments

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.Movie
import com.github.avanlex.fundamentalsassignments.data.loadMovies
import kotlinx.coroutines.*

class FragmentMoviesList : Fragment() {
    private lateinit var rvMovies : RecyclerView
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private lateinit var adapterMovies: MoviesRecyclerViewAdapter

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

    private fun initUi(v: View) {
        rvMovies = v.findViewById(R.id.rv_movie_list)
    }

    private fun loadMoviesData() {
        scope.launch {
            val movieList = loadMovies(view?.context!!)
            activity?.runOnUiThread{
                adapterMovies.bindMovies(movieList)
            }
        }
    }

    // You can vary the value held by the scalingFactor variable.
    // The larger the value the less no. of columns will be calculated and vice versa
    private fun calculateNoOfColumns(context: Context?): Int {
        val displayMetrics: DisplayMetrics = context!!.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val scalingFactor = 200
        val columnCount = (dpWidth / scalingFactor).toInt()
        // If column no. is less than 2, we still display 2 columns
        return if (columnCount >= 2) columnCount else 2
    }

    private fun initMoviesRecyclerView() {
        // Optimize performance a little
        rvMovies.setHasFixedSize(true)

        // Offset between items workaround
        val offset = resources.getDimension(R.dimen.movie_item_spacing).toInt()
        rvMovies.addItemDecoration(MoviesListItemOffsetDecorator(offset))

        val columns = calculateNoOfColumns(context)
        val layoutManager = GridLayoutManager(context, columns)
        rvMovies.layoutManager = layoutManager

        adapterMovies = MoviesRecyclerViewAdapter()
        adapterMovies.setOnOpenMovieDetailsClickListener{ movieItem -> openMovieDetails(movieItem)}
        rvMovies.adapter = adapterMovies
    }

    private fun openMovieDetails(movie: Movie) {
        fragmentManager!!.beginTransaction()
            .replace(R.id.main_activity, FragmentMoviesDetails.newInstance(movie))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null)
            .commit()
    }
}