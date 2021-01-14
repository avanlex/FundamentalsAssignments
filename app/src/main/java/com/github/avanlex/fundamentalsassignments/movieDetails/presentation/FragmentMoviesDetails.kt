package com.github.avanlex.fundamentalsassignments.movieDetails.presentation

import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.request.RequestOptions
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.R
import com.github.avanlex.fundamentalsassignments.VectorRatingBar
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie

class   FragmentMoviesDetails : Fragment() {

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
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_movie_placeholder)
            .fallback(R.drawable.ic_movie_placeholder)

        const val MOVIE_KEY = "MOVIE"
        fun newInstance(movie: Movie): FragmentMoviesDetails {
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
//        tvPg.text = requireContext().getString(R.string.string_pg, movie.adult ?: "18" : "0")
        tvTagline.text = movie.genres.joinToString { it.name }
        tvOverview.text = movie.overview
        vrbRating.rating = movie.rating  // movie.ratings is 10 degree rating
        tvReviews.text = getString(R.string.string_review_count, movie.votesCount)

        // Listener
        tvBack.setOnClickListener{ parentFragmentManager.popBackStack() }
    }
   
    private fun loadPoster(){
//        Glide.with(requireContext())
//            .load(movie.poster)
//            .apply(imageOption)
//            .into(poster)
        poster.load(
            BuildConfig.BASE_IMAGE_URL +
                "original" +
                movie.backdropPath
        ) {
            crossfade(true)
            placeholder(R.drawable.ic_image)
            error(R.drawable.ic_broken_image)
//            transformations(CircleCropTransformation())
        }
        poster.colorFilter = getGreyScaleFilter()
    }

    private fun getGreyScaleFilter() : ColorFilter{
        val matrix = ColorMatrix()
        matrix.setSaturation(0f)
        return ColorMatrixColorFilter(matrix)
    }

    private fun initActorsRecyclerView() {
        // Optimaze perfomance a little
        rvActors.setHasFixedSize(true)

        // Offset between items workaround
        val offset = resources.getDimension(R.dimen.actor_item_spacing).toInt()
        rvActors.addItemDecoration(ActorsListItemOffsetDecorator(offset))

        // Linear List
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvActors.layoutManager = layoutManager

        // Setting adapter to RecyclerView
        val actorsAdapter = ActorsRecyclerViewAdapter()
//        actorsAdapter.bindActors(movie.actors)
        actorsAdapter.bindActors(emptyList()) // TODO
        rvActors.adapter = actorsAdapter
    }

}