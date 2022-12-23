package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.children
import com.deathsdoor.ui_core.databinding.ItemCategoryBinding
import com.google.android.material.card.MaterialCardView
import kotlin.math.min

//TODO children not going below title
class Category(context: Context, attrs: AttributeSet): LinearLayout(context,attrs){
    private val _binding: ItemCategoryBinding
    init {
        _binding = ItemCategoryBinding.inflate(LayoutInflater.from(context),this,true)
        _binding.textView.text = "WORKS??"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        // Get the width and height mode and size
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // Initialize the width and height to the padding values
        var width = paddingLeft + paddingRight
        var height = paddingTop + paddingBottom

        // Measure the children views
        children.forEach {
            // Measure the child view
            measureChild(it, widthMeasureSpec, heightMeasureSpec)

            // Update the width and height based on the child view's dimensions
            width += it.measuredWidth
            height = height.coerceAtLeast(it.measuredHeight)
        }

        // Check if the width or height need to be adjusted based on the mode
        if (widthMode == MeasureSpec.EXACTLY) width = widthSize
        else if (widthMode == MeasureSpec.AT_MOST) width = min(width, widthSize)

        if (heightMode == MeasureSpec.EXACTLY) height = heightSize
        else if (heightMode == MeasureSpec.AT_MOST) height = min(height, heightSize)

        // Set the measured dimensions of the view
        setMeasuredDimension(width, height)
    }
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
         children.forEach {
             // Get its LayoutParams
             val params = it.layoutParams as LayoutParams

             // Determine the position of the child view based on its LayoutParams
             val childLeft = left + params.leftMargin
             val childTop = top + params.topMargin
             val childRight = childLeft + it.measuredWidth
             val childBottom = childTop + it.measuredHeight

             // Layout the child view
             it.layout(childLeft, childTop, childRight, childBottom)
         }
    }
}