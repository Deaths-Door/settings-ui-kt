package com.deathsdoor.ui_core.extras.interfaces

import android.widget.RadioGroup

interface OnRadioButtonLimitExceededListener {
    fun onRadioButtonLimitExceeded(radioGroup: RadioGroup, id: Int)
}