package com.astra.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_note.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_notes_view.*
import kotlinx.android.synthetic.main.activity_notes_view.subtitle_tv
import kotlinx.android.synthetic.main.activity_notes_view.title_tv
import kotlinx.android.synthetic.main.card_notes.view.*

class NotesViewActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_view)

        val extras = intent.extras
        val noteName = extras?.getString("name")
        val noteSubitile = extras?.getString("subtitle")
        val id = extras?.getString("id")
        val products = extras?.get("products") as ArrayList<String>
        val amounts = extras.get("amounts") as ArrayList<String>

        title_tv.setText(noteName)
        subtitle_tv.setText(noteSubitile)
        prod_rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@NotesViewActivity)
            adapter = ProductAdapter(this@NotesViewActivity, products, amounts)
        }

        add_btn.setOnClickListener {
            products.add("")
            amounts.add("")
            val intent = Intent(this, NotesViewActivity::class.java)
            intent.putExtra("name", noteName)
            intent.putExtra("subtitle", noteSubitile)
            intent.putExtra("products", products)
            intent.putExtra("amounts", amounts)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        save_btn.setOnClickListener {
            val note = hashMapOf(
                "Name" to noteName,
                "Subtitle" to noteSubitile,
                "Products" to products,
                "Amount" to amounts
            )

            db.collection("Notes").document("$id").set(note)
                .addOnSuccessListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Utils.showError(this, it.message.toString())
                }
        }
    }
}
