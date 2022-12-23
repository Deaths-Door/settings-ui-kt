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
import com.deathsdoor.ui_core.extras.functinos.Extensions.getPreferenceValue
import java.util.stream.Stream


object Extensions {
    //Extensions preference functions
    fun SharedPreferences.changePreference(key:String, value:Boolean) = this.edit().putBoolean(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:Int) = this.edit().putInt(key,value).commit()
    fun SharedPreferences.changePreference(key:String, value:String) = this.edit().putString(key,value).commit()
    fun Context.getPreference(name: String): SharedPreferences? = getSharedPreferences(name,Context.MODE_PRIVATE)
    fun SharedPreferences.getPreferenceValue(key:String): Any? =
        this.all.forEach { if(it.key == key) return it.value }


    private fun TextView.obSingleLine(): Boolean =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) this.isSingleLine
        else this.maxLines == 1
    internal fun TextView.setSingleLineCode(TRUE: (() -> Unit)? = null, FALSE: (() -> Unit)? = null){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) this.isSingleLine = !this.isSingleLine
        else if(this.maxLines == 1) this.maxLines = 5
        else this.maxLines = 1

        if(obSingleLine()) TRUE?.let { it() }
        else FALSE?.let { it() }
    }

    internal fun TypedArray.stringOrColor(attr: Int,string: () -> Unit, color: () -> Unit){
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

    internal fun Context.showSingleChoicePopUp(): Pair<ItemSingleChoiceBottomSheetBinding, PopupWindow> {
        val popupView = ItemSingleChoiceBottomSheetBinding.inflate(LayoutInflater.from(this))
        val popupWindow = PopupWindow(popupView.root, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.animationStyle = R.style.PopupAnimation
        return Pair(popupView,popupWindow)
    }

}