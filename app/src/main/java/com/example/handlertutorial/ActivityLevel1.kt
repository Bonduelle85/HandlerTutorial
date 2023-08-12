package com.example.handlertutorial

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handlertutorial.databinding.ActivityLevelBinding
import kotlin.random.Random

class ActivityLevel1 : AppCompatActivity() {
    lateinit var binding: ActivityLevelBinding

    private val handler = Handler(Looper.getMainLooper())
    private val token = Any()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enableDisableButton.setOnClickListener {
            Thread {
                handler.post { toggleTestButtonState() }
            }.start()
        }

        binding.randomColorButton.setOnClickListener {
            Thread {
                handler.post { nextRandomColor() }
            }.start()
        }

        binding.enableDisableDelayedButton.setOnClickListener {
            Thread {
                handler.postDelayed({ toggleTestButtonState() }, 2000L)
            }.start()
        }

        binding.randomColorDelayedButton.setOnClickListener {
            Thread{
                handler.postDelayed({ nextRandomColor() }, 2000L)
            }.start()
        }

        binding.randomColorTokenDelayedButton.setOnClickListener {
            Thread{
                handler.doPostDelayed({ toggleTestButtonState()}, token, 2000L)
            }.start()
        }
        binding.showToastButton.setOnClickListener {
            Thread{
                handler.doPostDelayed({ showToast() }, token, 2000L)
            }.start()
        }
        binding.cancelButton.setOnClickListener {
            Thread{
                handler.removeCallbacksAndMessages(token)
            }.start()
        }
    }

    private fun toggleTestButtonState() {
        binding.testButton.isEnabled = !binding.testButton.isEnabled
    }

    private fun nextRandomColor() {
        val randomColor = -Random.nextInt(255 * 255 * 255)
        binding.colorView.setBackgroundColor(randomColor)
    }

    private fun showToast() {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
    }

    // postDelayed: api >=28
    // extension method
    fun Handler.doPostDelayed(block: () -> Unit, token: Any, delay: Long) {
        val message = Message.obtain(this, block)
        message.obj = token
        sendMessageDelayed(message, delay)
    }
}