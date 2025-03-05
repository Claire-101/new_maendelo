package com.example.mendeleoapp

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find button and set click listener
        val button = findViewById<Button>(R.id.goToHome)
        button.setOnClickListener {
            val intent = Intent(this, details::class.java)
            startActivity(intent)
        }


        // TextView click listener (Navigate to HomeActivity)
        val textView = findViewById<TextView>(R.id.already_Have_account) // Make sure this ID matches your XML
        textView.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}