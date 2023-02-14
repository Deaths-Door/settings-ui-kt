package com.deathsdoor.ui_core.android

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView

//TODO complete this
class Switch(context:Context,attributeSet: AttributeSet? = null):FrameLayout(context,attributeSet) {
    constructor(context: Context) : this(context,null)
    private var composeView:ComposeView
    init {
        composeView = ComposeView(context,attributeSet)
        composeView.setContent {
          //  Switch()
        }
    }
}