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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.avanlex.fundamentalsassignments.data.Movie


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


    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_movie_placeholder)
            .fallback(R.drawable.ic_movie_placeholder)

        fun newInstance(movie: Movie): FragmentMoviesDetails{
            val fragment = FragmentMoviesDetails()
            fragment.movie = movie
            return fragment
        }
    }

    private fun setupUi(){
        view?.let {v->
            rvActors = v.findViewById(R.id.rv_actor_list)
            poster = v.findViewById(R.id.iv_details_poster)
            tvTitle = v.findViewById<TextView>(R.id.tv_details_movie_name)
            tvPg = v.findViewById<TextView>(R.id.tv_details_pg)
            tvTagline = v.findViewById<TextView>(R.id.tv_details_tagline)
            tvOverview = v.findViewById<TextView>(R.id.tv_details_storyline)
            vrbRating = v.findViewById<VectorRatingBar>(R.id.vrb_details_rating)
            tvReviews = v.findViewById<TextView>(R.id.tv_details_review_count)

            tvTitle.text = movie.title
            tvPg.text = context!!.getString(R.string.string_pg, movie.minimumAge)
            tvTagline.text = movie.genres.map{it.name}.joinToString()
            tvOverview.text = movie.overview
            vrbRating.rating = movie.ratings / 2 // movie.ratings is 10 degree rating
            tvReviews.text = getString(R.string.string_review_count, 0)

            // Listener
            v.findViewById<TextView>(R.id.text_back).setOnClickListener {
                fragmentManager?.popBackStack()
            }
        }
    }

    private fun loadPoster(){
//        Glide.with(view?.context)
//            .load(url)
//            .placeholder(R.drawable.default_profile)
//            .dontAnimate()
//            .into(view);
        Glide.with(context)
            .load(movie.poster)
            .apply(imageOption)
            .into(poster)
/*
        // Apply grayscale filter
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(matrix)
        poster.colorFilter = filter
*/
    }

    private fun loadSavedState(savedInstanceState: Bundle?){
        if (savedInstanceState != null) {
            // movie = savedInstanceState.getParcelable("MOVIE")
        }
    }

    private fun initActorsRecyclerView() {
        // Optimaze perfomance a little
        rvActors.setHasFixedSize(true)

        // Offset between items workaround
        rvActors.addItemDecoration(ActorsListItemOffsetDecorator(24))

        // Linear List
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvActors.layoutManager = layoutManager

        // Setting adapter to RecyclerView
        val actorsAdapter = ActorsRecyclerViewAdapter()
        actorsAdapter.bindActors(movie.actors)
        rvActors.adapter = actorsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadSavedState(savedInstanceState)
        setupUi()
        loadPoster()
        initActorsRecyclerView()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movies_details, container, false)


//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putParcelable("MOVIE", movie)
//        super.onSaveInstanceState(outState)
//    }


}