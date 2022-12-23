package com.deathsdoor.ui_core.views

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.deathsdoor.ui_core.Extensions.changePreference
import com.deathsdoor.ui_core.Extensions.setSingleLineCode
import com.deathsdoor.ui_core.Extensions.stringOrColor
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.annonations.ViewDoc
import com.deathsdoor.ui_core.databinding.ItemSwitchBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class Switch(context: Context, attrs: AttributeSet): FrameLayout(context,attrs){
    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Switch)

    private var titleView : TextView
    private var switchView: SwitchMaterial
    private var descriptionView: TextView

    private fun TextView.changeTitle(str:String) { this.text = str }
    private fun SwitchMaterial.switched(bool : Boolean) {
        this.isChecked = bool
        key?.let { preferenceName.changePreference(it,switchChecked) }
    }

    @ViewDoc(description = "name of key to save settings into")
    var key = typedArray.getString(R.styleable.Switch_key)

    @ViewDoc(description = "name of preference to save settings into")
    var preferenceName =  context.getSharedPreferences(typedArray.getString(R.styleable.Switch_preferenceName),Context.MODE_PRIVATE)

    @ViewDoc(description = "main title of the view")
    var title = typedArray.getString(R.styleable.Switch_title)
        set(value){
            field = value
            field?.let { titleView.changeTitle(it) }
        }

    @ViewDoc(description = "is the switch checked or not")
    var switchChecked = preferenceName.getBoolean(key,typedArray.getBoolean(R.styleable.Switch_switchChecked,false))
        set(value){
            field = value
            switchView.switched(switchChecked)
        }

    @ViewDoc(description = "color of switch when on")
    var switchOnColor = Color.GREEN

    @ViewDoc(description = "color of switch when off")
    var switchOffColor = Color.RED

    @ViewDoc(description = "should set short or detailed description")
    var useShortDescription = typedArray.getBoolean(R.styleable.Switch_useShortDescription,false)
        set(value){
            field = value
            descriptionView.setSingleLineCode(
                { descriptionView.text = shortDescription},
                { descriptionView.text = detailedDescription}
            )
        }

    @ViewDoc(description = "short description of setting")
    var shortDescription = typedArray.getString(R.styleable.Switch_shortDescription)

    @ViewDoc(description = "long and detailed description of setting")
    var detailedDescription = typedArray.getString(R.styleable.Switch_detailedDescription)

    init {
        ItemSwitchBinding.inflate(LayoutInflater.from(context),this,false)
            .also {
                titleView = it.title
                switchView = it.btnSwitch
                descriptionView = it.description

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

                descriptionView.setOnClickListener {
                    descriptionView.setSingleLineCode(
                        { useShortDescription = true},
                        { useShortDescription = false}
                    )
                }
            }

        typedArray.recycle()
    }
}
