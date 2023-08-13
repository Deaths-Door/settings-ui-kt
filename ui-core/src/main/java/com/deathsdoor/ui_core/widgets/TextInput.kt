package com.deathsdoor.ui_core.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.compose.ui.platform.LocalContext
import com.deathsdoor.ui_core.R
import com.deathsdoor.ui_core.databinding.ItemTextinputBinding
import com.deathsdoor.ui_core.databinding.PopupEdittextBinding
import com.deathsdoor.ui_core.extras.functinos.extensions.Extensions.showPopUpEditTextPopUP
import com.deathsdoor.ui_core.extras.interfaces.OnConfirmListener
import com.deathsdoor.ui_core.public.PreferenceExtensions.changePreference
import com.deathsdoor.ui_core.widgets.TextInput.Default.defaultDescription
import com.deathsdoor.ui_core.widgets.TextInput.Default.defaultHint
import com.deathsdoor.ui_core.widgets.TextInput.Default.defaultInputType
import com.deathsdoor.ui_core.widgets.TextInput.Default.defaultKey
import com.deathsdoor.ui_core.widgets.TextInput.Default.defaultPreference
import com.deathsdoor.ui_core.widgets.TextInput.Default.defaultTitle


//TODO document Category and finish it

class TextInput(context: Context, attrs: AttributeSet?): FrameLayout(context,attrs) {
    constructor(context: Context) : this(context,null)

    private val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextInput)

    private var titleView : TextView
    private var descriptionView : TextView
    private var editBtnView : ImageView


    var key = typedArray.defaultKey()
    private var preference = typedArray.defaultPreference(context)

    var title = typedArray.defaultTitle()
        set(value){
            field = value
            titleView.text = title
        }
    var description = typedArray.defaultDescription()
        set(value){
            field = value
            descriptionView.text = description
        }
    var inputType = typedArray.defaultInputType()
    var hint = typedArray.defaultHint()

    var onConfirmListener = object : OnConfirmListener{
        override fun onConfirm(input: String, window: PopupWindow): Boolean = true
        override fun onCancel(input: String, window: PopupWindow) = window.dismiss()
        override fun onDeny(_binding: PopupEdittextBinding, window: PopupWindow) {

            val currentBackground = (_binding.inputField.background as ColorDrawable).color
            _binding.inputField.setBackgroundColor(Color.RED)

            Toast.makeText(context,"DENIED",Toast.LENGTH_SHORT).show()

            // Vibrate the edit text view
            val vibrator =  context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
            else @Suppress("DEPRECATION") vibrator.vibrate(50)

            _binding.inputField.setBackgroundColor(currentBackground)
        }
    }
    private object Default{
        fun TypedArray.defaultKey() = this.getString(R.styleable.TextInput_key)
        fun TypedArray.defaultPreference(context: Context)
            = context.getSharedPreferences(this.getString(R.styleable.TextInput_preferenceName), Context.MODE_PRIVATE)
        fun TypedArray.defaultTitle() = this.getString(R.styleable.TextInput_title)
        fun TypedArray.defaultHint() = this.getString(R.styleable.TextInput_hint)
        fun TypedArray.defaultInputType() = this.getInt(R.styleable.TextInput_inputType,InputType.TYPE_CLASS_TEXT)
        fun TypedArray.defaultDescription() = this.getString(R.styleable.TextInput_description)
    }

    init {
        ItemTextinputBinding.inflate(LayoutInflater.from(context),this,true)
            .also {
                titleView = it.title
                descriptionView = it.description
                editBtnView = it.edit

                key = typedArray.defaultKey()
                preference = typedArray.defaultPreference(context)
                title = typedArray.defaultTitle()
                description = typedArray.defaultDescription()
                inputType = typedArray.defaultInputType()
                hint = typedArray.defaultHint()
                editBtnView.setOnClickListener {
                    val pair =  context.showPopUpEditTextPopUP(title,hint,inputType)
                    pair.first.btnOK.setOnClickListener {
                        if(onConfirmListener.onConfirm(pair.first.inputField.text.toString(),pair.second)){
                            key?.let { key -> preference.changePreference(key,pair.first.inputField.text.toString()) }
                            pair.second.dismiss()
                        }
                        else onConfirmListener.onDeny(pair.first,pair.second)
                    }
                    pair.second.setOnDismissListener {  onConfirmListener.onCancel(pair.first.inputField.text.toString(),pair.second) }
                }
            }
    }
}