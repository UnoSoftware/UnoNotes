package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private val auth = FirebaseAuth.getInstance()

    private val currentUser = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_btn: FloatingActionButton = findViewById(R.id.floatingActionButton)
        add_btn.setOnClickListener {
            val intent = Intent(this, CreateNote::class.java)
            startActivity(intent)
        }

        db.collection("Notes").get().addOnSuccessListener { notes ->
            val ids_documentos: MutableList<String> = mutableListOf()
            val names: MutableList<String> = mutableListOf()
            val subtitles: MutableList<String> = mutableListOf()
            val products: MutableList<ArrayList<String>> = mutableListOf()
            val amounts: MutableList<ArrayList<Int>> = mutableListOf()
            var T = 0
            for (note in notes) {
                T += 1
                ids_documentos.add(note.id)
            }
            for (i in ids_documentos) {
                db.collection("Notes").document(i).get().addOnSuccessListener {
                    if (it.get("UserID") == currentUser?.uid) {  // Solo se muestran las notas del usuario actual
                        names.add(it.get("Name").toString())
                        subtitles.add(it.get("Subtitle").toString())
                        products.add(it.get("Products") as ArrayList<String>)
                        amounts.add(it.get("Amounts") as ArrayList<Int>)
                        rv.apply{
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = NoteAdapter(this@MainActivity, names, subtitles, products, amounts, i)
                        }
                    } else {
                        T -= 1
                    }
                    // Si no hay notas, podríamos mostrar un mensaje
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout ->{
                auth.signOut()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
            R.id.settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}