package com.deathsdoor.ui_core.extras.interfaces

import com.google.android.material.switchmaterial.SwitchMaterial

interface OnSwitchToggleListener{
    fun onSwitchToggleOn(switchView: SwitchMaterial)
    fun onSwitchToggleOff(switchView: SwitchMaterial)
}
