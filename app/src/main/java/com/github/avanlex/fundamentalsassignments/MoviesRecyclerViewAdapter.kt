package com.github.avanlex.fundamentalsassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.avanlex.fundamentalsassignments.data.models.Movie
import com.google.android.material.imageview.ShapeableImageView

class MoviesRecyclerViewAdapter (
    private val clickListener: OnRecyclerMovieItemClicked
): RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = listOf()

    fun bindMovies(movieList: List<Movie>) {
        movies = movieList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (movies.size) {
            0 -> VIEW_TYPE_NO_MOVIES
            else -> VIEW_TYPE_MOVIES
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return when (viewType) {
            VIEW_TYPE_NO_MOVIES ->
                MovieEmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movies_empty, parent, false)
            )
            else -> MovieDataViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_movie, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        when (holder) {
            is MovieDataViewHolder -> {
                holder.onBind(movies[position])
                holder.itemView.setOnClickListener {
                    clickListener.onClick(movies[position])
                }
            }
            is MovieEmptyViewHolder -> { /* nothing to bind */ }
        }
    }

    override fun getItemCount(): Int = movies.size

}

interface OnRecyclerMovieItemClicked {
    fun onClick(movie: Movie)
}

private const val VIEW_TYPE_NO_MOVIES = 0
private const val VIEW_TYPE_MOVIES = 1

abstract class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class MovieEmptyViewHolder(itemView: View) : MovieViewHolder(itemView)
private class MovieDataViewHolder(itemView: View) : MovieViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.tv_card_movie_name)
    private val duration: TextView = itemView.findViewById(R.id.tv_card_duration)
    private val pg: TextView = itemView.findViewById(R.id.tv_card_pg)
    private val rating: VectorRatingBar = itemView.findViewById(R.id.vrb_details_rating)
    private val reviewCount: TextView = itemView.findViewById(R.id.tv_review_count)
    private val poster: ShapeableImageView = itemView.findViewById(R.id.siv_card_poster)

    fun onBind(movie: Movie) {
        name.text = movie.name
        duration.text = context.getString(R.string.string_duration, movie.duration)
        pg.text = movie.pg
        rating.rating = movie.rating.toFloat()
        reviewCount.text = context.getString(R.string.string_review_count, movie.reviewCount)
//        storyline.text = movie.storyline

        Glide.with(context)
            .load(movie.poster)
            .apply(imageOption)
            .into(poster)
        //poster.setImageResource(R.drawable.endgame_card)
    }

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_movie)
            .fallback(R.drawable.ic_movie)
            .circleCrop()
    }
}
