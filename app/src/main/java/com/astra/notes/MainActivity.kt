package com.astra.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cardview_1: CardView = findViewById(R.id.CardView)
        var add_btn: FloatingActionButton = findViewById(R.id.floatingActionButton)
        add_btn.setOnClickListener {
            cardview_1.setVisibility(View.VISIBLE)
        }
    }
}