package com.github.avanlex.fundamentalsassignments

import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.avanlex.fundamentalsassignments.data.Movie
import com.github.avanlex.fundamentalsassignments.data.loadMovies
import kotlinx.coroutines.*


class  FragmentMoviesDetails : Fragment() {
    private lateinit var movie: Movie
    private lateinit var rvActors : RecyclerView
    private lateinit var poster: ImageView
    private var movieID : Int = 0
    private lateinit var tvTitle: TextView
    private lateinit var tvPg: TextView
    private lateinit var tvTagline: TextView
    private lateinit var tvOverview: TextView
    private lateinit var vrbRating: VectorRatingBar
    private lateinit var tvReviews: TextView
    private lateinit var tvBack: TextView
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_movie_placeholder)
            .fallback(R.drawable.ic_movie_placeholder)

        val MOVIE_KEY = "MOVIE"
        fun newInstance(movie: Movie): FragmentMoviesDetails{
            val fragment = FragmentMoviesDetails()
            val args = Bundle()
            args.putParcelable(MOVIE_KEY, movie)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        val v = inflater.inflate(R.layout.fragment_movies_details, container, false)
        loadSavedState()
        setupUi(v)
        initActorsRecyclerView()
        loadPoster()
        return v
    }

    private fun loadSavedState(){
        movie = arguments?.getParcelable(MOVIE_KEY)!!
    }

    private fun setupUi(v : View){
        rvActors = v.findViewById(R.id.rv_actor_list)
        poster = v.findViewById(R.id.iv_details_poster)
        tvTitle = v.findViewById(R.id.tv_details_movie_name)
        tvPg = v.findViewById(R.id.tv_details_pg)
        tvTagline = v.findViewById(R.id.tv_details_tagline)
        tvOverview = v.findViewById(R.id.tv_details_storyline)
        vrbRating = v.findViewById(R.id.vrb_details_rating)
        tvReviews = v.findViewById(R.id.tv_details_review_count)
        tvBack = v.findViewById(R.id.text_back)

        tvTitle.text = movie.title
        tvPg.text = context!!.getString(R.string.string_pg, movie.minimumAge)
        tvTagline.text = movie.genres.joinToString { it.name }
        tvOverview.text = movie.overview
        vrbRating.rating = movie.ratings  // movie.ratings is 10 degree rating
        tvReviews.text = getString(R.string.string_review_count, movie.numberOfRatings)

        // Listener
        tvBack.setOnClickListener{ fragmentManager?.popBackStack() }
    }
   
    private fun loadPoster(){
        Glide.with(context)
            .load(movie.poster)
            .apply(imageOption)
            .into(poster)
        poster.colorFilter = getGreyScaleFilter()
    }

    private fun getGreyScaleFilter() : ColorFilter{
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        return ColorMatrixColorFilter(matrix)
    }

    private fun loadMoviesData() {
        scope.launch {
            val movieList = loadMovies(view?.context!!)
            activity?.runOnUiThread{
                movie = movieList.find{ it.id == movieID }!!
            }
        }
    }

    private fun initActorsRecyclerView() {
        // Optimaze perfomance a little
        rvActors.setHasFixedSize(true)

        // Offset between items workaround
        val offset = resources.getDimension(R.dimen.movie_item_spacing).toInt()
        rvActors.addItemDecoration(ActorsListItemOffsetDecorator(offset))

        // Linear List
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvActors.layoutManager = layoutManager

        // Setting adapter to RecyclerView
        val actorsAdapter = ActorsRecyclerViewAdapter()
        actorsAdapter.bindActors(movie.actors)
        rvActors.adapter = actorsAdapter
    }
}