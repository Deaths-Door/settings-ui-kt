package com.deathsdoor.myapplication

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.deathsdoor.myapplication.databinding.ActivityMainBinding
import com.deathsdoor.myapplication.databinding.ActivityMainBinding.inflate
import com.deathsdoor.ui_core.public.SettingBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
     //  SettingBuilder.show(binding.root.id,R.layout.setting_test,supportFragmentManager)
    }
}