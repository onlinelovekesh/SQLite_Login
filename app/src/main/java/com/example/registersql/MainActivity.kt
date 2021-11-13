package com.example.registersql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button=findViewById<Button>(R.id.btn1)

        button.setOnClickListener {
            var i=Intent(this, Registration::class.java)
            startActivity(i)
        }
    }
}