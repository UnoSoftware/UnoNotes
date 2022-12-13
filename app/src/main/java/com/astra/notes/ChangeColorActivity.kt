package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore

class ChangeColorActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_color)

        var view = findViewById<View>(R.id.LayoutChangeColor)
        var textView = findViewById<View>(R.id.textView6)

        if(MainActivity.globalDark){
            view.setBackgroundColor(Color.parseColor("#FF000000"))
            textView.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }else{
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            textView.setBackgroundColor(Color.parseColor("#FF000000"))
        }

        var Red: Button = findViewById(R.id.red_btn)
        var Blue: Button = findViewById(R.id.blue_btn)
        var Yellow: Button = findViewById(R.id.yellow_btn)
        var Green: Button = findViewById(R.id.green_btn)
        val extras = intent.extras
        val noteId = extras?.getString("id")
        val noteName = extras?.getString("name")
        val userID = extras?.get("iduser") as ArrayList<String>
        val noteSubtitle = extras?.getString("subtitle")
        val products = extras?.get("products") as ArrayList<String>
        val amounts = extras.get("amounts") as ArrayList<Int>

        Red.setOnClickListener {
            db.collection("Notes").document("$noteId").update("Color", "#CB0900")
            .addOnSuccessListener {
                val intent = Intent(this, NotesViewActivity::class.java)
                intent.putExtra("name", noteName)
                intent.putExtra("subtitle", noteSubtitle)
                intent.putExtra("products", products)
                intent.putExtra("amounts", amounts)
                intent.putExtra("color", "#CB0900")
                intent.putExtra("iduser", userID)
                startActivity(intent)
            }
            .addOnFailureListener {
                Utils.showError(this, it.message.toString())
            }
        }

        Blue.setOnClickListener {
            db.collection("Notes").document("$noteId").update("Color", "#074AA3")
                .addOnSuccessListener {
                    val intent = Intent(this, NotesViewActivity::class.java)
                    intent.putExtra("name", noteName)
                    intent.putExtra("subtitle", noteSubtitle)
                    intent.putExtra("products", products)
                    intent.putExtra("amounts", amounts)
                    intent.putExtra("color", "#074AA3")
                    intent.putExtra("iduser", userID)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }
        }

        Yellow.setOnClickListener {
            db.collection("Notes").document("$noteId").update("Color", "#F0D804")
                .addOnSuccessListener {
                    val intent = Intent(this, NotesViewActivity::class.java)
                    intent.putExtra("name", noteName)
                    intent.putExtra("subtitle", noteSubtitle)
                    intent.putExtra("products", products)
                    intent.putExtra("amounts", amounts)
                    intent.putExtra("color", "#F0D804")
                    intent.putExtra("iduser", userID)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }
        }

        Green.setOnClickListener {
            db.collection("Notes").document("$noteId").update("Color", "#328A10")
                .addOnSuccessListener {
                    val intent = Intent(this, NotesViewActivity::class.java)
                    intent.putExtra("name", noteName)
                    intent.putExtra("subtitle", noteSubtitle)
                    intent.putExtra("products", products)
                    intent.putExtra("amounts", amounts)
                    intent.putExtra("color", "#328A10")
                    intent.putExtra("iduser", userID)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }
        }

    }
}