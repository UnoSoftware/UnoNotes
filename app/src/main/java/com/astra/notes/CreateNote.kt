package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_change_color.*
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

        var view = findViewById<View>(R.id.LayoutCreateNote)
        var viewImageDark = findViewById<View>(R.id.imageView13)
        var viewImageLight = findViewById<View>(R.id.imageView12)

        if(MainActivity.globalDark){
            view.setBackgroundColor(Color.parseColor("#FF000000"))
            viewImageLight.setVisibility(View.GONE)
            viewImageDark.setVisibility(View.VISIBLE)
        }else{
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            viewImageLight.setVisibility(View.VISIBLE)
            viewImageDark.setVisibility(View.GONE)
        }

        val create_btn: Button = findViewById(R.id.create_btn)

        imageView12.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        imageView13.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        create_btn.setOnClickListener{
            val noteName = title_tv.text.toString()
            val noteSubtitle = subtitle_tv.text.toString()
            val products: ArrayList<String> = arrayListOf()
            val amount: ArrayList<Int> = arrayListOf()
            val checks: ArrayList<Boolean> = arrayListOf()
            val users: ArrayList<String> = arrayListOf()
            currentUser?.uid?.let { it1 -> users.add(it1) }
            val note = hashMapOf(
                "Name" to noteName,  // Guarda el nombre de la nota
                "Subtitle" to noteSubtitle,  // Guarda la descripcion de la nota
                "Products" to products,   // Guarda el Array de productos de la nota
                "Amount" to amount,   // Guarda el Array de cantidades de la nota
                "Checks" to checks, // Guarda las notas marcadas
                "Color" to "#F88B39", // Guarda el Color por defecto para la nota
                "UserID" to users  // Se guarda el ID del usuario que crea la nota
            )

            db.collection("Notes").add(note)
                .addOnSuccessListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Utils.showError(this, it.message.toString()) // Si se produce un error al guardar muestra el fallo
                }
        }
    }
}