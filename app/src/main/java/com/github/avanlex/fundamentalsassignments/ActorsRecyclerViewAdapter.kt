package com.github.avanlex.fundamentalsassignments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Actor
import com.github.avanlex.fundamentalsassignments.data.models.Movie
import java.lang.reflect.Field


class ActorsRecyclerViewAdapter (
        private val clickListener: OnRecyclerActorItemClicked
):  RecyclerView.Adapter<ActorViewHolder>() {

    private var actors: List<Actor> = listOf()

    fun bindActors(actorList: List<Actor>) {
        actors = actorList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (actors.size) {
            0 -> VIEW_TYPE_NO_ACTORS
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return when (viewType) {
            VIEW_TYPE_NO_ACTORS ->
                ActorEmptyViewHolder(
                        LayoutInflater.from(parent.context)
                                .inflate(R.layout.item_actors_empty, parent, false)
                )
            else -> ActorDataViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.view_holder_actor, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        when (holder) {
            is ActorDataViewHolder -> {
                holder.onBind(actors[position])
                holder.itemView.setOnClickListener {
                    clickListener.onClick(actors[position])
                }
            }
            is ActorEmptyViewHolder -> { /* nothing to bind */ }
        }
    }

    override fun getItemCount(): Int = actors.size
}

interface OnRecyclerActorItemClicked {
    fun onClick(actor: Actor)
}

val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_NO_ACTORS = 0
private const val VIEW_TYPE_ACTORS = 1

abstract class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class ActorEmptyViewHolder(itemView: View) : ActorViewHolder(itemView)
private class ActorDataViewHolder(itemView: View) : ActorViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(actor: Actor) {
        name.text = actor.name
         val avatarId = context.resources.getIdentifier(actor.avatar,
                                                      "drawable",
                                                       context.packageName)
        avatar.setImageDrawable(ContextCompat.getDrawable(context, avatarId))
    }
}