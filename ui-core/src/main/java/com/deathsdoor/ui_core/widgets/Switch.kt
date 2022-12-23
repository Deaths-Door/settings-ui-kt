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
import com.deathsdoor.ui_core.extras.functinos.Extensions.changePreference
import com.deathsdoor.ui_core.extras.functinos.Extensions.setSingleLineCode
import com.deathsdoor.ui_core.extras.functinos.Extensions.stringOrColor
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.extras.annonations.ViewDoc
import com.deathsdoor.ui_core.databinding.ItemSwitchBinding
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultDetailedDescription
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultKey
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultPreference
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultShortDescription
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultSwitchChecked
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultTitle
import com.deathsdoor.ui_core.widgets.Switch.Default.defaultUseShortDescription
import com.google.android.material.switchmaterial.SwitchMaterial

//TODO add switch on off color change
class Switch(context: Context, attrs: AttributeSet): FrameLayout(context,attrs){
    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Switch)

    private var titleView : TextView
    private var switchView: SwitchMaterial
    private var descriptionView: TextView

    @ViewDoc(description = "name of key to save settings into")
    var key = typedArray.defaultKey()
        //typedArray.getString(R.styleable.Switch_key)

    @ViewDoc(description = "name of preference to save settings into")
    var preference =  typedArray.defaultPreference(context)
        //context.getSharedPreferences(typedArray.getString(R.styleable.Switch_preferenceName),Context.MODE_PRIVATE)

    @ViewDoc(description = "main title of the view")
    var title = typedArray.defaultTitle()
        set(value){
            field = value
            field?.let { titleView.changeTitle(it) }
        }

    @ViewDoc(description = "is the switch checked or not")
    var switchChecked = typedArray.defaultSwitchChecked(preference,key)
        set(value){
            field = value
            switchView.switched(switchChecked)
        }
    //preference.getBoolean(key,typedArray.getBoolean(R.styleable.Switch_switchChecked,false))


    @ViewDoc(description = "color of switch when on")
    var switchOnColor = Color.GREEN

    @ViewDoc(description = "color of switch when off")
    var switchOffColor = Color.RED

    @ViewDoc(description = "should set short or detailed description")
    var useShortDescription = typedArray.defaultUseShortDescription()
        set(value){
            field = value
            if(useShortDescription) descriptionView.text = shortDescription
            else descriptionView.text = detailedDescription
        }

    @ViewDoc(description = "short description of setting")
    var shortDescription = typedArray.defaultShortDescription()
        set(value){
            field = value
            if(useShortDescription) descriptionView.text = field
        }

    @ViewDoc(description = "long and detailed description of setting")
    var detailedDescription = typedArray.defaultDetailedDescription()
        set(value){
            field = value
            if(!useShortDescription) descriptionView.text = field
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
