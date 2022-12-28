package com.deathsdoor.ui_core.extras.interfaces

import android.widget.PopupWindow
import com.deathsdoor.ui_core.databinding.PopupEdittextBinding

interface OnConfirmListener {
    fun onConfirm(input: String,window: PopupWindow) : Boolean
    fun onCancel(input: String,window: PopupWindow)
    fun onDeny(_binding: PopupEdittextBinding, window: PopupWindow)
}