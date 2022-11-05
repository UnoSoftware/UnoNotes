package com.astra.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_note.*
import java.util.*
import kotlin.collections.ArrayList

class CreateNote : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private val auth = FirebaseAuth.getInstance()

    private val currentUser = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        val create_btn: Button = findViewById(R.id.create_btn)

        create_btn.setOnClickListener{
            val noteName = title_tv.text.toString()
            val noteSubtitle = subtitle_tv.text.toString()
            val products: ArrayList<String> = arrayListOf()
            var amount: ArrayList<Int> = arrayListOf()
            val note = hashMapOf(
                "Name" to noteName,
                "Subtitle" to noteSubtitle,
                "Products" to products,
                "Amount" to amount,
                "UserID" to currentUser?.uid  // Se guarda el ID del usuario que crea la nota
            )

            db.collection("Notes").add(note)
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