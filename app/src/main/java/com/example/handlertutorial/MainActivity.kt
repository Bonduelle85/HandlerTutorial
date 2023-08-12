package com.example.handlertutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.handlertutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.handlerLevel1Button.setOnClickListener {
            startActivity(Intent(this, ActivityLevel1::class.java))
        }
        binding.handlerLevel2Button.setOnClickListener {
            startActivity(Intent(this, ActivityLevel2::class.java))
        }
    }

    companion object {
        @JvmStatic private val DELAY = 2000L // milliseconds
    }
}