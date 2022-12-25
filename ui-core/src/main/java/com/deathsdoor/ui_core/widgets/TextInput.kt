package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.deathsdoor.ui_core.databinding.ItemTextinputBinding


class TextInput(context: Context, attrs: AttributeSet): FrameLayout(context,attrs) {
    var titleView : TextView
    var descriptionView : TextView
    var editView : ImageView

   // var title = TODO()
    //var description = TODO()
    //var inputType = TODO()
    init {
        ItemTextinputBinding.inflate(LayoutInflater.from(context),this,true)
            .also {
                titleView = it.title
                descriptionView = it.description
                editView = it.edit

                editView.setOnClickListener {

                }
            }
    }
}