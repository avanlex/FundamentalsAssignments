package com.github.avanlex.fundamentalsassignments


import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.github.avanlex.fundamentalsassignments.data.models.Movie


class  FragmentMoviesDetails : Fragment() {
    private lateinit var movie: Movie
    private lateinit var rvActors : RecyclerView
    private lateinit var poster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvPg: TextView
    private lateinit var tvTagline: TextView
    private lateinit var tvOverview: TextView
    private lateinit var vrbRating: VectorRatingBar
    private lateinit var tvReviews: TextView
    private lateinit var tvBack: TextView

    companion object {
        val MOVIE_KEY = "MOVIE"
        fun newInstance(movie: Movie): FragmentMoviesDetails{
            val fragment = FragmentMoviesDetails()
            fragment.movie = movie
            fragment.arguments?.putParcelable(MOVIE_KEY, movie)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        val v = inflater.inflate(R.layout.fragment_movies_details, container, false)
        loadSavedState(savedInstanceState)
        setupUi(v)
        loadPoster(v)
        initActorsRecyclerView()
        return v
    }

    private fun loadSavedState(savedInstanceState: Bundle?){
        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable(MOVIE_KEY)!!
        }
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

        tvTitle.text = movie.name
        tvPg.text = movie.pg
        tvTagline.text = movie.tagline
        tvOverview.text = movie.storyline
        vrbRating.rating = movie.rating.toFloat()
        tvReviews.text = getString(R.string.string_review_count, movie.reviewCount)

        // Listener
        tvBack.setOnClickListener{ fragmentManager?.popBackStack() }
    }
    
    private fun loadPoster(v: View) {
        poster.setImageDrawable( ContextCompat.getDrawable(v.context, movie.poster))
        // Apply grayscale filter
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(matrix)
        poster.colorFilter = filter
    }

    private fun initActorsRecyclerView() {
        // Optimaze perfomance a little
        rvActors.setHasFixedSize(true)

        // Offset between items workaround
        rvActors.addItemDecoration(ActorsListItemOffsetDecorator(R.dimen.actor_item_spacing))

        // Linear List
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvActors.layoutManager = layoutManager

        // Setting adapter to RecyclerView
        val moviesAdapter = ActorsRecyclerViewAdapter()
        movie.let { moviesAdapter.bindActors(it.actors) }
        rvActors.adapter = moviesAdapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("MOVIE", movie)
        super.onSaveInstanceState(outState)
    }
}