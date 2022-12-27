package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.stringOrColor
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.databinding.ItemSwitchBinding
import com.deathsdoor.ui_core.extras.interfaces.OnSwitchToggleListener
import com.deathsdoor.ui_core.public.PreferenceExtensions.changePreference
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultDetailedDescription
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultKey
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultPreference
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultShortDescription
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultSwitchChecked
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultTitle
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultUseShortDescription
import com.google.android.material.switchmaterial.SwitchMaterial

//TODO add switch on off color change
class Switch(context: Context, attrs: AttributeSet?): FrameLayout(context,attrs){
    constructor(context: Context) : this(context,null)

    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Switch)

    private var titleView : TextView
    private var switchView: SwitchMaterial
    private var descriptionView: TextView

    var key = typedArray.defaultKey()

    var preference = typedArray.defaultPreference(context)

    var title = typedArray.defaultTitle()
        set(value){
            field = value
            field?.let { titleView.changeTitle(it) }
        }

    var switchChecked = typedArray.defaultSwitchChecked(preference,key)
        set(value){
            field = value
            switchView.switched(switchChecked)
        }

    var switchOnColor = Color.GREEN

    var switchOffColor = Color.RED

    var useShortDescription = typedArray.defaultUseShortDescription()
        set(value){
            field = value
            if(useShortDescription) {
                toggleListener.onSwitchToggleOn(switchView)
                descriptionView.text = shortDescription

            } else {
                toggleListener.onSwitchToggleOff(switchView)
                descriptionView.text = detailedDescription
            }
        }

    var shortDescription = typedArray.defaultShortDescription()
        set(value){
            field = value
            if(useShortDescription) descriptionView.text = field
        }

    var detailedDescription = typedArray.defaultDetailedDescription()
        set(value){
            field = value
            if(!useShortDescription) descriptionView.text = field
        }

    var showWarnWhenSwitchToggledOn = false
    var showWarnWhenSwitchToggledOff = false

    var showWarnWhenSwitchToggledOnMsg = ""
    var showWarnWhenSwitchToggledOffMsg = ""

    var toggleListener =  object :OnSwitchToggleListener{
        override fun onSwitchToggleOn(switchView: SwitchMaterial) {
            if(showWarnWhenSwitchToggledOn) Toast.makeText(context,showWarnWhenSwitchToggledOnMsg,Toast.LENGTH_SHORT).show()
        }
        override fun onSwitchToggleOff(switchView: SwitchMaterial) {
            if(showWarnWhenSwitchToggledOff) Toast.makeText(context,showWarnWhenSwitchToggledOffMsg,Toast.LENGTH_SHORT).show()
        }
    }


    private object Default{
        fun TypedArray.defaultKey() = this.getString(R.styleable.Switch_key)
        fun TypedArray.defaultPreference(context: Context) = context.getSharedPreferences(this.getString(R.styleable.Switch_preferenceName),Context.MODE_PRIVATE)
        fun TypedArray.defaultTitle() = this.getString(R.styleable.Switch_title)
        fun TypedArray.defaultSwitchChecked(preferences: SharedPreferences, key: String?) = preferences.getBoolean(key,this.getBoolean(R.styleable.Switch_switchChecked,false))
        fun TypedArray.defaultShortDescription() = this.getString(R.styleable.Switch_shortDescription)
        fun TypedArray.defaultDetailedDescription() = this.getString(R.styleable.Switch_detailedDescription)
        fun TypedArray.defaultUseShortDescription() = this.getBoolean(R.styleable.Switch_useShortDescription,false)
    }

    private fun TextView.changeTitle(str:String) { this.text = str }
    private fun SwitchMaterial.switched(bool : Boolean) {
        this.isChecked = bool
        key?.let { preference.changePreference(it,switchChecked) }
    }

    init {
        ItemSwitchBinding.inflate(LayoutInflater.from(context),this,true)
            .also {
                titleView = it.title
                switchView = it.btnSwitch
                descriptionView = it.description

                key = typedArray.defaultKey()
                preference = typedArray.defaultPreference(context)
                title = typedArray.defaultTitle()
                switchChecked = typedArray.defaultSwitchChecked(preference,key)

                useShortDescription = typedArray.defaultUseShortDescription()
                shortDescription = typedArray.defaultShortDescription()
                detailedDescription = typedArray.defaultDetailedDescription()

                typedArray.stringOrColor(
                    R.styleable.Switch_switchOnColor,
                    { switchOnColor = Color.parseColor(typedArray.getString(R.styleable.Switch_switchOnColor)) },
                    { switchOnColor = typedArray.getColor(R.styleable.Switch_switchOnColor,switchOnColor) },
                )

                typedArray.stringOrColor(
                    R.styleable.Switch_switchOffColor,
                    { switchOffColor = Color.parseColor(typedArray.getString(R.styleable.Switch_switchOffColor)) },
                    { switchOffColor = typedArray.getColor(R.styleable.Switch_switchOffColor,switchOffColor) },
                )

                switchView.setOnCheckedChangeListener { _, bool ->
                    switchChecked = bool
                }

                it.root.setOnClickListener {
                    useShortDescription = !useShortDescription
                }
            }
        typedArray.recycle()
    }
}
