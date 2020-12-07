package com.github.avanlex.fundamentalsassignments

import android.content.Context
import android.util.AttributeSet

class SquareImageView : com.google.android.material.imageview.ShapeableImageView {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        val width = measuredWidth
        setMeasuredDimension(width, width)
    }
}