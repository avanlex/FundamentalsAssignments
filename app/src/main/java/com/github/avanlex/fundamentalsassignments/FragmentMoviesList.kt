package com.github.avanlex.fundamentalsassignments

import android.os.Bundle
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
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        initUi(view)
        initMoviesRecyclerView()
        return view
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

    private fun initMoviesRecyclerView() {
        // Optimaze perfomance a little
        rvMovies.setHasFixedSize(true)

        // Offset between items workaround
        val offset = resources.getDimension(R.dimen.movie_item_spacing).toInt()
        rvMovies.addItemDecoration(MoviesListItemOffsetDecorator(offset))

        val columns = 2
        val layoutManager = GridLayoutManager(context, columns)
        rvMovies.layoutManager = layoutManager

        val adapterMovies = MoviesRecyclerViewAdapter()
        adapterMovies.setOnClickListener{ movieItem ->
             openMovieDetails(movieItem)
        }
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