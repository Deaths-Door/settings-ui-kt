package com.deathsdoor.ui_core.extras.interfaces

import android.widget.RadioGroup

interface OnLimitExceededListener {
    fun onLimitExceeded(radioGroup: RadioGroup, id: Int)
}