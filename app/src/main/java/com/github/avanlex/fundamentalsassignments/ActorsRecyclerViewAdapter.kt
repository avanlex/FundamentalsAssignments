package com.github.avanlex.fundamentalsassignments

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.avanlex.fundamentalsassignments.data.models.Actor
import java.lang.reflect.Field


class ActorsRecyclerViewAdapter(
    private val actors: List<Actor>
) : RecyclerView.Adapter<ActorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

val RecyclerView.ViewHolder.context
    get() = this.itemView.context

abstract class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class ActorEmptyViewHolder(itemView: View) : ActorViewHolder(itemView)
private class ActorDataViewHolder(itemView: View) : ActorViewHolder(itemView) {

    private val avatar: ImageView = itemView.findViewById(R.id.iv_actor_photo)
    private val name: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(actor: Actor) {
        name.text = actor.name
        avatar.setImageDrawable(ContextCompat.getDrawable(context, getResId(actor.avatar, R.drawable::class.java)))
    }

}

fun getResId(resName: String, c: Class<*>): Int {
    return try {
        val idField: Field = c.getDeclaredField(resName)
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}