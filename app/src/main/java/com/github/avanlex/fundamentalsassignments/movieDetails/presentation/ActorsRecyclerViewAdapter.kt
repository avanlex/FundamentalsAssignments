package com.github.avanlex.fundamentalsassignments.movieDetails.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.avanlex.fundamentalsassignments.R
import com.github.avanlex.fundamentalsassignments.context
import com.github.avanlex.fundamentalsassignments.movieList.data.Actor

class ActorsRecyclerViewAdapter :  RecyclerView.Adapter<ActorViewHolder>() {

    private var actors: List<Actor> = listOf()

    fun bindActors(actorList: List<Actor>) {
        actors = actorList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_actor, parent, false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size
}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)

    companion object {
        private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_movie_placeholder)
            .fallback(R.drawable.ic_movie_placeholder)
    }

    fun onBind(actor: Actor) {
        name.text = actor.name
        Glide.with(context)
            .load(actor.picture)
            .apply(imageOption)
            .into(avatar)

    }

}