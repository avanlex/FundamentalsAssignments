package com.github.avanlex.fundamentalsassignments.movieList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.avanlex.fundamentalsassignments.BuildConfig
import com.github.avanlex.fundamentalsassignments.R
import com.github.avanlex.fundamentalsassignments.VectorRatingBar
import com.github.avanlex.fundamentalsassignments.context
import com.github.avanlex.fundamentalsassignments.movieList.data.Movie
import com.google.android.material.imageview.ShapeableImageView

class MoviesRecyclerViewAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private var onOpenDetailsClickListener: OnItemClickListener? = null
    private var onAddToFavoriteClickListener: OnItemAddToFavoriteClickListener? = null
    private var movies: List<Movie> = listOf()

    fun setOnOpenMovieDetailsClickListener(listener: OnItemClickListener?){
        if (listener != null) {
            onOpenDetailsClickListener = listener
        }
    }

    fun setAddToFavoriteClickListener(listener: OnItemAddToFavoriteClickListener?){
        if (listener != null) {
            onAddToFavoriteClickListener = listener
        }
    }

    fun bindMovies(movieList: List<Movie>) {
        movies = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(movies[position],
                onOpenDetailsClickListener,
                onAddToFavoriteClickListener
        )
    }

    override fun getItemCount(): Int = movies.size
}

fun interface OnItemClickListener {
    fun onClick(movie: Movie)
}

fun interface OnItemAddToFavoriteClickListener {
    fun onClick(movie: Movie, layoutPosition: Int)
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.tv_card_movie_name)
    private val duration: TextView = itemView.findViewById(R.id.tv_card_duration)
    private val tagline: TextView = itemView.findViewById(R.id.tv_card_tagline)
    private val pg: TextView = itemView.findViewById(R.id.tv_card_pg)
    private val rating: VectorRatingBar = itemView.findViewById(R.id.vrb_details_rating)
    private val reviewCount: TextView = itemView.findViewById(R.id.tv_review_count)
    private val poster: ShapeableImageView = itemView.findViewById(R.id.siv_card_poster)
    private val favorite: ImageView = itemView.findViewById(R.id.iv_favorite)

    fun bindItem(movie: Movie,
                 onOpenDetails: OnItemClickListener?,
                 onAddToFavorite: OnItemAddToFavoriteClickListener?
    ) {
        tagline.text = movie.genres.joinToString { it.name }
        name.text = movie.title
        duration.text = context.getString(R.string.string_duration, movie.runtime)
        pg.text = if (movie.adult) {context.getString(R.string.string_pg, 18)} else ""

        rating.rating = movie.rating
        reviewCount.text = context.getString(R.string.string_review_count, movie.votesCount)
        val color = if (movie.favorite) R.color.pink else R.color.color_white1
        DrawableCompat.setTint(favorite.drawable, ContextCompat.getColor(context, color))

        itemView.setOnClickListener { onOpenDetails?.onClick(movie) }
        favorite.setOnClickListener { onAddToFavorite?.onClick(movie, layoutPosition) }

        poster.load(movie.posterPath) {
            crossfade(true)
            placeholder(R.drawable.ic_image)
            error(R.drawable.ic_broken_image)
        }
    }
}
