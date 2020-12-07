package com.github.avanlex.fundamentalsassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movies[position])
        holder.itemView.setOnClickListener {
            clickListener.onClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size
}

interface OnRecyclerMovieItemClicked {
    fun onClick(movie: Movie)
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

        val posterId = context.resources
            .getIdentifier(movie.poster,"drawable", context.packageName)
        poster.setImageDrawable(ContextCompat.getDrawable(context, posterId))
    }
}
