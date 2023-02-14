package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.databinding.ItemChoiceBinding
import com.deathsdoor.ui_core.extras.Choice
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.addChildren
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.setLimit
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.showChoicePopUp
import com.deathsdoor.ui_core.extras.interfaces.OnLimitExceededListener
import com.deathsdoor.ui_core.widgets.Choice.Defaults.defaultDescription
import com.deathsdoor.ui_core.widgets.Choice.Defaults.defaultKey
import com.deathsdoor.ui_core.widgets.Choice.Defaults.defaultLimit
import com.deathsdoor.ui_core.widgets.Choice.Defaults.defaultPreferenceName
import com.deathsdoor.ui_core.widgets.Choice.Defaults.defaultTitle

class Choice(context: Context, attrs: AttributeSet?): FrameLayout(context,attrs){
    constructor(context: Context) : this(context,null)
    private val typedArray = context.obtainStyledAttributes(attrs,R.styleable.Choice)

    private var titleView : TextView? = null
    private var descriptionView : TextView? = null
    private var radioGroupView: RadioGroup? = null
        set(value){
            field = value
            radioGroupView?.addChildren(items)
            radioGroupView?.setLimit(limit,whenLimitExceed)
        }

    var key = typedArray.defaultKey()

    var preferenceName =  typedArray.defaultPreferenceName(context)

    var title = typedArray.defaultTitle()
        set(value){
            field = value
            field?.let { titleView?.changeTitle(it) }
        }

    var description : String? = typedArray.defaultDescription()
        set(value){
            field = value
            descriptionView?.changeTitle(field)
        }

    var limit = typedArray.defaultLimit()
        set(value){
            field = value
            radioGroupView?.setLimit(field,whenLimitExceed)
        }

    var whenLimitExceed = object: OnLimitExceededListener {
        override fun onLimitExceeded(radioGroup: RadioGroup, id: Int) {
            Toast.makeText(context,"LIMIT EXCEEDED", Toast.LENGTH_SHORT).show()
        }
    }

    var items: ArrayList<Choice> = arrayListOf()
        set(value){
            field = value
            radioGroupView?.addChildren(field)
            radioGroupView?.setLimit(limit,whenLimitExceed)
        }

    private object Defaults{
        fun TypedArray.defaultKey() = this.getString(R.styleable.Choice_key)
        fun TypedArray.defaultPreferenceName(context: Context): SharedPreferences? = context.getSharedPreferences(this.getString(R.styleable.Choice_preferenceName), Context.MODE_PRIVATE)
        fun TypedArray.defaultTitle() = this.getString(R.styleable.Choice_title)
        fun TypedArray.defaultDescription() = this.getString(R.styleable.Choice_description)
        fun TypedArray.defaultLimit() = this.getInt(R.styleable.Choice_limit,1)

    }
    private fun TextView.changeTitle(str: String?) { this.text = str }


    init{
        ItemChoiceBinding.inflate(LayoutInflater.from(context),this,true)
            .also {

                key = typedArray.defaultKey()
                preferenceName = typedArray.defaultPreferenceName(context)
                title = typedArray.defaultTitle()
                description = typedArray.defaultDescription()

                it.root.setOnClickListener {
                    val smooth =  context.showChoicePopUp()
                    val binding = smooth.first
                    titleView = binding.title
                    descriptionView = binding.description
                    radioGroupView = binding.rg
                }
            }
    }
}