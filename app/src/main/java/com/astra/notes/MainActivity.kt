package com.astra.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private val auth = FirebaseAuth.getInstance()

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
            val currentUserId = auth.currentUser?.uid
            var T = 0
            for (note in notes) {
                T += 1
                ids_documentos.add(note.id)
            }
            for (i in 0 until T) {
                db.collection("Notes").document(ids_documentos[i]).get().addOnSuccessListener { note ->
                    if (note.get("UserID") == currentUserId) {
                        names.add(note.get("Name") as String)
                        subtitles.add(note.get("Subtitle") as String)
                        products.add(note.get("Products") as ArrayList<String>)
                        amounts.add(note.get("Amount") as ArrayList<Int>)
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