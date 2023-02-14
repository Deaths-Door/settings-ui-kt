package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.marginTop
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.databinding.ItemCategoryBinding
import kotlin.math.min

//TODO children not going below title
class Category(context: Context, attrs: AttributeSet): LinearLayout(context,attrs){
    init {
        val textView = TextView(context)
        textView.id = 0
        textView.setPadding(5,5,5,5)
        textView.text = "TITLE"
        textView.textSize = 36f

        val parms = textView.layoutParams as MarginLayoutParams
        parms.setMargins(10,10,10,10)
        textView.layoutParams = parms

        this.addView(textView)

    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        // Get the width and height mode and size
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // Initialize the width and height to the padding values
        var width = paddingLeft + paddingRight + this.findViewById<TextView>(0).width
        var height = paddingTop + paddingBottom+ this.findViewById<TextView>(0).height

        // Measure the children views
        children.forEach {
            // Measure the child view
            measureChild(it, widthMeasureSpec, heightMeasureSpec)

            // Update the width and height based on the child view's dimensions
            width += it.measuredWidth
            height = height.coerceAtLeast(it.measuredHeight) //it.measuredHeight //h
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
        // Layout the child views
        children.forEach {
            // Get its LayoutParams
            val params = it.layoutParams as LayoutParams

            // Determine the position of the child view based on its LayoutParams
            val childLeft = left + params.leftMargin
            val childRight = childLeft + it.measuredWidth
            val childBottom = top + it.measuredHeight

            // Layout the child view
            it.layout(childLeft, top, childRight, childBottom)
        }
         /*children.forEach {
             // Get its LayoutParams
             val params = it.layoutParams as LayoutParams

             // Determine the position of the child view based on its LayoutParams
             val childLeft = left + params.leftMargin             val childTop = top + params.topMargin

             val childRight = childLeft + it.measuredWidth
             val childBottom = childTop + it.measuredHeight

             // Layout the child view
             it.layout(childLeft, childTop, childRight, childBottom)
         }*/
    }
}