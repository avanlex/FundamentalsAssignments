package com.github.avanlex.fundamentalsassignments

import android.content.Context

data class ResourcesProvider (val context: Context){


    fun getString(stringId: Int): String
    {
        return context.getString(stringId);
    }
}