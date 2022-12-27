package com.deathsdoor.ui_core.extras.functinos.extensions

import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import com.deathsdoor.ui_core.databinding.ItemSingleChoiceBottomSheetBinding
import com.deathsdoor.ui_core.databinding.PopupEdittextBinding
import com.deathsdoor.ui_core.extras.Choice
import com.deathsdoor.ui_core.extras.functinos.extensions.ImageExtensions.loadImg
import com.deathsdoor.ui_core.extras.interfaces.OnRadioButtonLimitExceededListener

object Extensions {
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

    internal fun Context.showPopUpEditTextPopUP(title: String, hint: String, inputType: Int): Pair<PopupEdittextBinding, PopupWindow> {
        val popupViewBinding = PopupEdittextBinding.inflate(LayoutInflater.from(this))

        popupViewBinding.title.text = title
        popupViewBinding.inputField.hint = hint
        popupViewBinding.inputField.inputType = inputType

        val popupWindow = PopupWindow(popupViewBinding.root, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true
        popupWindow.showAtLocation(popupViewBinding.root, Gravity.CENTER, 0, 0)
        return Pair(popupViewBinding,popupWindow)

    }

    internal fun Context.showSingleChoicePopUp(): Pair<ItemSingleChoiceBottomSheetBinding, PopupWindow> {
        val popupViewBinding = ItemSingleChoiceBottomSheetBinding.inflate(LayoutInflater.from(this))
        val popupWindow = PopupWindow(popupViewBinding.root, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true
        popupWindow.showAtLocation(popupViewBinding.root, Gravity.CENTER, 0, 0)
        return Pair(popupViewBinding,popupWindow)
    }

    internal fun RadioGroup.setLimit(max:Int,hörter:OnRadioButtonLimitExceededListener){
        this.setOnCheckedChangeListener { radioGroup, id ->
            if(id == -1) return@setOnCheckedChangeListener

            var checkedCount = 0
            radioGroup.children.forEach {
                val rb = it as RadioButton
                if(rb.isChecked) checkedCount++

                if(checkedCount > max) hörter.onRadioButtonLimitExceeded(radioGroup,id)
            }
        }
    }

    internal fun RadioGroup.addChildren(children:List<Choice>){
        this.removeAllViews()
        children.forEachIndexed { index, it ->
            val rb = RadioButton(this.context)
            rb.text = it.text
            rb.id = index

            rb.setOnCheckedChangeListener { view, bool ->
                rb.isEnabled = bool
                if(bool) rb.setButtonDrawable(android.R.drawable.btn_radio)
                else rb.loadImg(it.image)
            }
            this.addView(rb)
        }
    }
}


