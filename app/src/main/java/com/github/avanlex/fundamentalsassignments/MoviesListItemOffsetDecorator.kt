package com.github.avanlex.fundamentalsassignments

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MoviesListItemOffsetDecorator(
    private val offset: Int
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = 0
        outRect.left = offset
        outRect.top = offset
        outRect.bottom = offset
    }
}
