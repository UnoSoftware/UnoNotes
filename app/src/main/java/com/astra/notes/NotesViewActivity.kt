package com.astra.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes_view.*

class NotesViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_view)

        val extras = intent.extras
        val noteName = extras?.getString("name")
        val noteSubitile = extras?.getString("subtitle")
        val products = extras?.get("products") as ArrayList<String>
        val amounts = extras?.get("amounts") as ArrayList<String>

        title_tv.setText(noteName)
        subtitle_tv.setText(noteSubitile)
        prod_rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@NotesViewActivity)
            adapter = ProductAdapter(this@NotesViewActivity, products, amounts)
        }
    }
}
