package com.fadhil.juzamma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fadhil.juzamma.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startButton()
    }

    private fun startButton() {
        binding.btnStart.setOnClickListener{
            val i = Intent(this, AllSurahActivity::class.java)
            startActivity(i)
        }
    }
}