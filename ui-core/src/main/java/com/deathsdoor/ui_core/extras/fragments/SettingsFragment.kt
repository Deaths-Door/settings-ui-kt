package com.deathsdoor.ui_core.extras.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


const val LAYOUT = "layout"
class SettingsFragment : Fragment() {
    private var layout:Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            layout = it.getInt(LAYOUT)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return if(layout != null) inflater.inflate(layout!!,container,false)
        else View(context)// TODO
    }

    companion object {
        @JvmStatic
        fun newInstance(layout:Int? = null) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    layout?.let { putInt(LAYOUT, it) }
                }
            }
    }
}