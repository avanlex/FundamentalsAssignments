package com.github.avanlex.fundamentalsassignments

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Actor

class MoviesRecyclerView : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

abstract class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class MovieEmptyViewHolder(itemView: View) : ActorViewHolder(itemView)
private class MovieDataViewHolder(itemView: View) : ActorViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)

    data class Movie(
        val name: String,
        val year: Int,
        val duration: Int,
        val pg: String,
        val rating: Int,
        val reviewCount: Int,
        val storyline: String,
        val poster: String
    )

    fun onBind(actor: Actor) {
        name.text = actor.name
        avatar.setImageDrawable(ContextCompat.getDrawable(context, getResId(actor.avatar, R.drawable::class.java)))
    }

}
