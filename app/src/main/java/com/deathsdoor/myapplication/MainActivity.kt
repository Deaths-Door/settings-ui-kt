package com.deathsdoor.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deathsdoor.myapplication.databinding.ActivityMainBinding
import com.deathsdoor.myapplication.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        binding.compress.setContent {
        }
    }
}