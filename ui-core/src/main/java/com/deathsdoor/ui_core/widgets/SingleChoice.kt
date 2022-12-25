package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.databinding.ItemSingleChoiceBinding
import com.deathsdoor.ui_core.extras.Choice
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.addChildren
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.setLimit
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.showSingleChoicePopUp
import com.deathsdoor.ui_core.extras.interfaces.OnRadioButtonLimitExceededListener
import com.deathsdoor.ui_core.widgets.SingleChoice.Defaults.defaultDescription
import com.deathsdoor.ui_core.widgets.SingleChoice.Defaults.defaultKey
import com.deathsdoor.ui_core.widgets.SingleChoice.Defaults.defaultPreferenceName
import com.deathsdoor.ui_core.widgets.SingleChoice.Defaults.defaultTitle

class SingleChoice(context: Context, attrs: AttributeSet) : LinearLayout(context,attrs) {
    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SingleChoice)

    private var titleView : TextView? = null
    private var descriptionView : TextView? = null
    private var radioGroupView:RadioGroup? = null

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

    var items: ArrayList<Choice> = arrayListOf()
        set(value){
            field = value
            radioGroupView?.addChildren(field)
            radioGroupView?.setLimit(1,whenLimitExceed)
        }
    val whenLimitExceed = object:OnRadioButtonLimitExceededListener{
        override fun onRadioButtonLimitExceeded(radioGroup: RadioGroup, id: Int) {
            Toast.makeText(context,"LIMIT EXCEEDED",Toast.LENGTH_SHORT).show()
        }
    }

    private object Defaults{
        fun TypedArray.defaultKey() = this.getString(R.styleable.SingleChoice_key)
        fun TypedArray.defaultPreferenceName(context: Context): SharedPreferences? = context.getSharedPreferences(this.getString(R.styleable.SingleChoice_preferenceName), Context.MODE_PRIVATE)
        fun TypedArray.defaultTitle() = this.getString(R.styleable.SingleChoice_title)
        fun TypedArray.defaultDescription() = this.getString(R.styleable.SingleChoice_description)
    }

    private fun TextView.changeTitle(str: String?) { this.text = str }

    init{
        ItemSingleChoiceBinding.inflate(LayoutInflater.from(context),this,true)
            .also {

                key = typedArray.defaultKey()
                preferenceName = typedArray.defaultPreferenceName(context)
                title = typedArray.defaultTitle()
                description = typedArray.defaultDescription()

                it.root.setOnClickListener {
                    val smooth =  context.showSingleChoicePopUp()
                    val binding = smooth.first
                    titleView = binding.title
                    descriptionView = binding.description
                    radioGroupView = binding.rg
                }
            }
    }
}
