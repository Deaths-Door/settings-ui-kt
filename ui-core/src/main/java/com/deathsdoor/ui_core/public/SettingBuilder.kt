package com.deathsdoor.ui_core.public

import android.graphics.Color
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.deathsdoor.ui_core.extras.fragments.SettingsFragment
import com.deathsdoor.ui_core.extras.functinos.extensions.AttributesBuilder
import com.deathsdoor.ui_core.extras.interfaces.OnSwitchToggleListener
import com.google.android.material.switchmaterial.SwitchMaterial

const val NON_NESTED_SETTING = "non_nested"

data class CustomSettingView(val type:TYPE,val extraAttributes:Map<String,Any>)

enum class TYPE{
    SWITCH,
    SINGLECHOICE,
    CATEGORY,
    TEXTINPUT
}

object SettingBuilder {
    //For non-nested settings or settings where all attributes aer given via xml
    fun show(rootID:Int, layout:Int,fragmentManager: FragmentManager)
            = fragmentManager.beginTransaction().replace(rootID, SettingsFragment.newInstance(layout),NON_NESTED_SETTING).commit()

    fun SwitchSetting(
        title: String,
        shortDescription: String,
        detailedDescription: String = shortDescription,
        useShortDescription: Boolean = true,
        switchChecked: Boolean = false,
        switchOnColor: Int = Color.GREEN,
        switchOffColor: Int = Color.RED,
        showWarnWhenSwitchToggledOn: Boolean = false,
        showWarnWhenSwitchToggledOff: Boolean = false,
        showWarnWhenSwitchToggledOnMsg: String = "WARNING",
        showWarnWhenSwitchToggledOffMsg: String = "WARNING",
        toggleListener: OnSwitchToggleListener = object : OnSwitchToggleListener {
            override fun onSwitchToggleOn(switchView: SwitchMaterial) { if(showWarnWhenSwitchToggledOn) Toast.makeText(switchView.context,showWarnWhenSwitchToggledOnMsg, Toast.LENGTH_SHORT).show() }
            override fun onSwitchToggleOff(switchView: SwitchMaterial) { if(showWarnWhenSwitchToggledOff) Toast.makeText(switchView.context,showWarnWhenSwitchToggledOffMsg, Toast.LENGTH_SHORT).show() }
        }
        ): CustomSettingView = CustomSettingView(TYPE.SWITCH,
                mapOf(
                    "title" to title,
                    "shortDescription" to shortDescription,
                    "detailedDescription" to detailedDescription,
                    "useShortDescription" to useShortDescription,
                    "switchChecked" to switchChecked,
                    "switchOnColor" to switchOnColor,
                    "switchOffColor" to switchOffColor,
                    "showWarnWhenSwitchToggledOn" to showWarnWhenSwitchToggledOn,
                    "showWarnWhenSwitchToggledOff" to showWarnWhenSwitchToggledOff,
                    "showWarnWhenSwitchToggledOnMsg" to showWarnWhenSwitchToggledOnMsg,
                    "showWarnWhenSwitchToggledOffMsg" to showWarnWhenSwitchToggledOffMsg,
                    "toggleListener" to toggleListener
                )
    )
}

fun CustomSettingView.createAttributeSet(): AttributesBuilder.AttributeSet
    = AttributesBuilder.AttributeSet( this.extraAttributes)
