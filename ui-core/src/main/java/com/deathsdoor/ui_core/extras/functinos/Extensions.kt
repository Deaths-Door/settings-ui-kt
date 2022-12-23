package com.deathsdoor.ui_core.extras.functinos

import android.content.SharedPreferences
import android.content.res.TypedArray
import android.os.Build
import android.util.TypedValue
import android.widget.TextView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.databinding.ItemSingleChoiceBottomSheetBinding


object Extensions {
    fun SharedPreferences.changePreference(key:String, value:Boolean) = this.edit().putBoolean(key,value).commit()

    fun TextView.obSingleLine(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) this.isSingleLine
        else this.maxLines == 1
    fun TextView.setSingleLineCode(TRUE: (() -> Unit)? = null, FALSE: (() -> Unit)? = null){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.isSingleLine = !this.isSingleLine
            if(obSingleLine()) TRUE?.let { it() }
            else FALSE?.let { it() }
        } else if(this.maxLines == 1) {
            this.maxLines = 5
            FALSE?.let { it() }
        } else {
            this.maxLines = 1
            TRUE?.let { it() }
        }
    }

    fun TypedArray.stringOrColor(attr: Int,string: () -> Unit, color: () -> Unit){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            when(this.getType(attr)){
                TypedValue.TYPE_STRING -> string()
                TypedValue.TYPE_INT_COLOR_ARGB4, TypedValue.TYPE_INT_COLOR_RGB4 -> color()
            }
        }
        else{
            if (this.hasValue(attr)) {
                val switchOffColorResId = this.getResourceId(attr, 0)
                if (switchOffColorResId != 0) string()
                else color()
            }
        }
    }

    fun Context.showSingleChoicePopUp(): Pair<ItemSingleChoiceBottomSheetBinding, PopupWindow> {
        val popupView = ItemSingleChoiceBottomSheetBinding.inflate(LayoutInflater.from(this))
        val popupWindow = PopupWindow(popupView.root, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.animationStyle = R.style.PopupAnimation
        return Pair(popupView,popupWindow)
    }

}