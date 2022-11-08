package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_notes_view.*
import kotlinx.android.synthetic.main.activity_notes_view.subtitle_tv
import kotlinx.android.synthetic.main.activity_notes_view.title_tv

class NotesViewActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_view)

        val extras = intent.extras
        var noteName = extras!!.getString("name")
        var noteSubtitle = extras.getString("subtitle")
        val id = extras.getString("id")
        val color = extras.getString("color")
        val userID = extras.getString("iduser")
        val products = extras.get("products") as ArrayList<String>
        val amounts = extras.get("amounts") as ArrayList<Int>
        val change_color_btn: ImageButton = findViewById(R.id.change_color_btn)
        val card_vw: MaterialCardView = findViewById(R.id.CardView)

        card_vw.setBackgroundColor(Color.parseColor(color)) // Establece el color de la nota que esta guardado en la base de datos

        title_tv.setText(noteName)
        subtitle_tv.setText(noteSubtitle)
        prod_rv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@NotesViewActivity)
            adapter = ProductAdapter(this@NotesViewActivity, products, amounts as ArrayList<Long>) // Llama al recyclerview de los productos
        }

        imageView4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        add_btn.setOnClickListener {
            products.add("")
            amounts.add(1)
            val intent = Intent(this, NotesViewActivity::class.java)
            intent.putExtra("name", noteName)
            intent.putExtra("subtitle", noteSubtitle)
            intent.putExtra("products", products)
            intent.putExtra("amounts", amounts)
            intent.putExtra("color", color)
            intent.putExtra("id", id)
            intent.putExtra("iduser", userID)
            startActivity(intent)
        }

        change_color_btn.setOnClickListener {
            val intent = Intent(this, ChangeColorActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("name", noteName)
            intent.putExtra("color", color)
            intent.putExtra("subtitle", noteSubtitle)
            intent.putExtra("products", products)
            intent.putExtra("amounts", amounts)
            startActivity(intent)
        }

        save_btn.setOnClickListener {
            noteName = title_tv.text.toString()
            noteSubtitle = subtitle_tv.text.toString()
            val note = hashMapOf(
                "Name" to noteName,
                "Subtitle" to noteSubtitle,
                "Products" to products,
                "Amount" to amounts,
                "Color" to color,
                "UserID" to userID
            )
            db.collection("Notes").document(id!!).set(note)
                .addOnSuccessListener {
                    Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Utils.showError(this, it.message.toString())
                }
        } 
    }
}
