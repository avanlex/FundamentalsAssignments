package com.github.avanlex.fundamentalsassignments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Actor

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

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(actor: Actor) {
        name.text = actor.name
        avatar.setImageDrawable(ContextCompat.getDrawable(context, actor.avatar))
    }
}