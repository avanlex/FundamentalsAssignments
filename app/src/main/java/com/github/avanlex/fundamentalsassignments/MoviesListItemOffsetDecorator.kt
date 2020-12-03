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
    ) {/*
        if (parent.getChildAdapterPosition(view) % 2  == 0) {
            // отступ для левого столбца
            outRect.right = 0;
            outRect.left = offset;
        }*/
//        else {
//            // отступ для правого столбца
//            outRect.right = offset;
//            outRect.left = 0;
//        }
        outRect.right = 0;
        outRect.left = offset;
        outRect.top = 0;
        outRect.bottom = offset;
    }
}
