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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var add_btn: FloatingActionButton = findViewById(R.id.floatingActionButton)
        add_btn.setOnClickListener {
            val intent = Intent(this, CreateNote::class.java)
            startActivity(intent)
        }

        db.collection("Notes").get().addOnSuccessListener { notes ->
            var ids_documentos: MutableList<String> = mutableListOf()
            var names: MutableList<String> = mutableListOf()
            var subtitles: MutableList<String> = mutableListOf()
            var products: MutableList<ArrayList<String>> = mutableListOf()
            var amounts: MutableList<ArrayList<Int>> = mutableListOf()
            var T = 0
            for (note in notes) {
                T += 1
                ids_documentos.add(note.id)
            }
            for (i in ids_documentos) {
                db.collection("Notes").document(i).get().addOnSuccessListener {
                    names.add(it.get("Name") as String)
                    subtitles.add(it.get("Subtitle") as String)
                    products.add(it.get("Products") as ArrayList<String>)
                    amounts.add(it.get("Amount") as ArrayList<Int>)
                    rv.apply{
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = NoteAdapter(this@MainActivity, names, subtitles, products, amounts, i)

                    }
                }
            }
        }
    }
}