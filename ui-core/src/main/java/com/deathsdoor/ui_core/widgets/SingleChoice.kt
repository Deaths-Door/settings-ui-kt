package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.extras.annonations.ViewDoc
import com.deathsdoor.ui_core.databinding.ItemSingleChoiceBinding
import com.deathsdoor.ui_core.extras.functinos.Extensions.showSingleChoicePopUp
import com.deathsdoor.ui_core.widgets.SingleChoice.Defaults.defaultKey
import com.deathsdoor.ui_core.widgets.SingleChoice.Defaults.defaultPreferenceName
import com.deathsdoor.ui_core.widgets.SingleChoice.Defaults.defaultTitle


//TODO finish implementation
class SingleChoice(context: Context, attrs: AttributeSet) : LinearLayout(context,attrs) {
    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleChoice)

    private var titleView : TextView? = null
    private var descriptionView : TextView? = null

    @ViewDoc(description = "name of key to save settings into")
    var key = typedArray.defaultKey()
    //typedArray.getString(R.styleable.Switch_key)

    @ViewDoc(description = "name of preference to save settings into")
    var preferenceName =  typedArray.defaultPreferenceName(context)
    //context.getSharedPreferences(typedArray.getString(R.styleable.Switch_preferenceName),Context.MODE_PRIVATE)

    @ViewDoc(description = "main title of the view")
    var title = typedArray.defaultTitle()
        set(value){
            field = value
            field?.let { titleView?.changeTitle(it) }
        }

    @ViewDoc(description = "description of setting")
    var description :String = TODO()
        set(value){
            field = value
            descriptionView?.changeTitle(field)
        }

    private object Defaults{
        fun TypedArray.defaultKey() = this.getString(R.styleable.SingleChoice_key)
        fun TypedArray.defaultPreferenceName(context: Context) = context.getSharedPreferences(this.getString(R.styleable.SingleChoice_preferenceName), Context.MODE_PRIVATE)
        fun TypedArray.defaultTitle() = this.getString(R.styleable.SingleChoice_title)
    }

    private fun TextView.changeTitle(str:String) { this.text = str }

    init{
        ItemSingleChoiceBinding.inflate(LayoutInflater.from(context),this,false)
            .also {
                it.root.setOnClickListener {
                    val smth =  context.showSingleChoicePopUp()
                    val binding = smth.first
                    titleView = binding.title
                    descriptionView = binding.description
                }
            }
    }
}