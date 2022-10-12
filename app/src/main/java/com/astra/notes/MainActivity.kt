package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cardview_1: CardView = findViewById(R.id.CardView)
        var add_btn: FloatingActionButton = findViewById(R.id.floatingActionButton)
        var edit_color: ImageButton = findViewById(R.id.edit_color_btn)
        add_btn.setOnClickListener {
            cardview_1.setVisibility(View.VISIBLE)
        }
        var changed = intent.getStringExtra("changed").toBoolean()
        val intent = Intent(this, this::class.java)
        intent.putExtra("color", "#FFFFFF")
        if (changed == true){
            var color = intent.getStringExtra("color").toString()
        }
        //cardview_1.setCardBackgroundColor(Color.parseColor(color))

        edit_color.setOnClickListener {
            val intent = Intent(this, ChangeColorActivity::class.java)
            startActivity(intent)
        }
    }
}