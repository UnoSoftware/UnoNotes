package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class ShareActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private val auth = FirebaseAuth.getInstance()

    lateinit var noteName: String
    lateinit var noteSubtitle: String
    lateinit var id: String
    lateinit var color: String
    lateinit var userID: ArrayList<String>
    lateinit var products: ArrayList<String>
    lateinit var amounts: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        var view = findViewById<View>(R.id.ShareView)

        if (MainActivity.globalDark) {
            view.setBackgroundColor(Color.parseColor("#FF000000"))
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }

        var add_btn = findViewById<Button>(R.id.save_btn2)
        add_btn.setOnClickListener {
            var id_txt = findViewById<EditText>(R.id.id_txt)
            var id = id_txt.text.toString()
            db.collection("Notes").document(id).get()
                .addOnSuccessListener { note ->
                    userID = note.get("UserID") as ArrayList<String>
                    auth.currentUser?.uid?.let { it1 -> userID.add(it1) }
                    noteName = (note.get("Name") as String)
                    color = note.get("Color") as String
                    noteSubtitle = note.get("Subtitle") as String
                    products = note.get("Products") as ArrayList<String>
                    amounts = note.get("Amount") as ArrayList<Int>
                    val note = hashMapOf(
                        "Name" to noteName,
                        "Subtitle" to noteSubtitle,
                        "Products" to products,
                        "Amount" to amounts,
                        "Color" to color,
                        "UserID" to userID
                    )
                    db.collection("Notes").document(id).set(note)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Utils.showError(this, it.message.toString())
                        }
                }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}