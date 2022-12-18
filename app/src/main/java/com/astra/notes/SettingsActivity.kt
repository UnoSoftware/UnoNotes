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
import kotlinx.android.synthetic.main.activity_notes_view.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var view = findViewById<View>(R.id.LayoutSettings)
        val btn = findViewById<Switch>(R.id.switchColor)
        var viewImageDark = findViewById<View>(R.id.imageView6)
        var viewImageLight = findViewById<View>(R.id.imageView7)


        if(globalDark){
            view.setBackgroundColor(Color.parseColor("#FF000000"))
            btn.setTextColor(Color.parseColor("#FFFFFFFF"))
            viewImageLight.setVisibility(View.GONE)
            viewImageDark.setVisibility(View.VISIBLE)
            btn.setChecked(true)
        }else{
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            btn.setTextColor(Color.parseColor("#FF000000"))
            viewImageLight.setVisibility(View.VISIBLE)
            viewImageDark.setVisibility(View.GONE)
            btn.setChecked(false)
        }

        imageView6.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        imageView7.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn.setOnCheckedChangeListener { _, isChecked ->

            if (btn.isChecked) {
                view.setBackgroundColor(Color.parseColor("#FF000000"))
                btn.setTextColor(Color.parseColor("#FFFFFFFF"))
                viewImageLight.setVisibility(View.GONE)
                viewImageDark.setVisibility(View.VISIBLE)
                globalDark = true
            } else {
                view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                btn.setTextColor(Color.parseColor("#FF000000"))
                viewImageLight.setVisibility(View.VISIBLE)
                viewImageDark.setVisibility(View.GONE)
                globalDark = false
            }
        }
    }
}