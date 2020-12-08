package com.github.avanlex.fundamentalsassignments

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ActorsListItemOffsetDecorator(
    private val offset: Int
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = offset
        outRect.left = 0
        outRect.top = offset
        outRect.bottom = offset
    }
}
