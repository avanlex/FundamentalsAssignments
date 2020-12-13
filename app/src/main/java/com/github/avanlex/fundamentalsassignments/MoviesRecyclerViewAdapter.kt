package com.github.avanlex.fundamentalsassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.avanlex.fundamentalsassignments.data.Movie
import com.google.android.material.imageview.ShapeableImageView

class MoviesRecyclerViewAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private lateinit var onOpenDetailsClickListener: OnItemClickListener
    private var movies: List<Movie> = listOf()

    fun setOnOpenMovieDetailsClickListener(listener : OnItemClickListener?){
        if (listener != null) {
            onOpenDetailsClickListener = listener
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
        holder.onBind(movies[position],
            onOpenDetailsClickListener,
            {
                movies[position].favorite = ! movies[position].favorite
                notifyDataSetChanged()
            }
        )
    }

    override fun getItemCount(): Int = movies.size
}

fun interface OnItemClickListener {
    fun onClick(movie: Movie)
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

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_movie_placeholder)
            .fallback(R.drawable.ic_movie_placeholder)
    }

    fun onBind(movie: Movie,
               onOpenDetails: OnItemClickListener,
               onAddToFavorite: OnItemClickListener
    ) {
        tagline.text = movie.genres.joinToString { it.name }
        name.text = movie.title
        duration.text = context.getString(R.string.string_duration, movie.runtime)
        pg.text = context.getString(R.string.string_pg, movie.minimumAge)
        rating.rating = movie.ratings
        reviewCount.text = context.getString(R.string.string_review_count, movie.numberOfRatings)
        val color = if (movie.favorite) R.color.pink else R.color.color_white1
        DrawableCompat.setTint(favorite.drawable, ContextCompat.getColor(context, color))

        itemView.setOnClickListener { onOpenDetails.onClick(movie) }
        favorite.setOnClickListener { onAddToFavorite.onClick(movie) }

        Glide.with(context)
            .load(movie.poster)
            .apply(imageOption)
            .into(poster)
    }
}
