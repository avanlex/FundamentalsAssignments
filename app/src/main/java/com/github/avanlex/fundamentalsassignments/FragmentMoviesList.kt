package com.github.avanlex.fundamentalsassignments

//import com.github.avanlex.fundamentalsassignments.data.models.Movie
//import com.github.avanlex.fundamentalsassignments.domain.MoviesDataSource
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
    ): View  = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
        initMoviesRecyclerView()
        loadMoviesData()
    }

    private fun initUi() {
        rvMovies = view?.findViewById(R.id.rv_movie_list)!!
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
        rvMovies.addItemDecoration(MoviesListItemOffsetDecorator(24))

        val layoutManager = GridLayoutManager(context, 2)
        rvMovies.layoutManager = layoutManager

        // Setting adapter to RecyclerView
        adapterMovies = MoviesRecyclerViewAdapter(clickListener)
        rvMovies.adapter = adapterMovies
    }

    private fun doOnClick(movie: Movie) {
        rvMovies.let {
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