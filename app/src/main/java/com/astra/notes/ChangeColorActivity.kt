package com.astra.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChangeColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_color)

        var Red: Button = findViewById(R.id.red_btn)
        var Blue: Button = findViewById(R.id.blue_btn)
        var Yellow: Button = findViewById(R.id.yellow_btn)
        var Green: Button = findViewById(R.id.green_btn)

        Red.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("color", "#CB0900")
            startActivity(intent)
        }
        Blue.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("color", "#074AA3")
            startActivity(intent)
        }
        Yellow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("color", "#F0D804")
            startActivity(intent)
        }
        Green.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("color", "#328A10")
            intent.putExtra("changed", true)
            startActivity(intent)
        }

    }
}