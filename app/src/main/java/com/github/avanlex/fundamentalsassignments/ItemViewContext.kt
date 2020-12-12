package com.github.avanlex.fundamentalsassignments

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context