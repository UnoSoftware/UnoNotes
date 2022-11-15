package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.astra.notes.MainActivity.Companion.globalDark

class SettingsActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        backButton = findViewById(R.id.backButton)
        val btn = findViewById<Switch>(R.id.switchColor)

        var view = findViewById<View>(R.id.LayoutSettings)
        if(globalDark){
            view.setBackgroundColor(Color.parseColor("#FF000000"))
            btn.setTextColor(Color.parseColor("#FFFFFFFF"))
            btn.setChecked(true)
        }else{
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            btn.setTextColor(Color.parseColor("#FF000000"))
            btn.setChecked(false)
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn.setOnCheckedChangeListener { _, isChecked ->

            if (btn.isChecked) {
                view.setBackgroundColor(Color.parseColor("#FF000000"))
                btn.setTextColor(Color.parseColor("#FFFFFFFF"))
                globalDark = true
            } else {
                view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                btn.setTextColor(Color.parseColor("#FF000000"))
                globalDark = false
            }
        }
    }
}