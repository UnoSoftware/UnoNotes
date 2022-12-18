package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var globalDark = true
    }

    private val db = FirebaseFirestore.getInstance()

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var view = findViewById<View>(R.id.LayoutMainActivity)

        if(globalDark){
            view.setBackgroundColor(Color.parseColor("#FF000000"))
        }else{
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }

        val add_btn: FloatingActionButton = findViewById(R.id.floatingActionButton)
        add_btn.setOnClickListener {
            val intent = Intent(this, CreateNote::class.java)
            startActivity(intent)
        }

        val swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swrly)

        fun load() {
            db.collection("Notes").get().addOnSuccessListener { notes ->
                val ids_documentos: MutableList<String> = mutableListOf()
                val names: MutableList<String> = mutableListOf()
                val subtitles: MutableList<String> = mutableListOf()
                val colors: MutableList<String> = mutableListOf()
                val products: MutableList<ArrayList<String>> = mutableListOf()
                val amounts: MutableList<ArrayList<Int>> = mutableListOf()
                val checks: MutableList<ArrayList<Boolean>> = mutableListOf()
                val ids: MutableList<ArrayList<String>> = mutableListOf()
                val currentUserId = auth.currentUser?.uid
                var T = 0
                for (note in notes) {
                    T += 1
                    ids_documentos.add(note.id)
                }
                var ids_documentos_final: MutableList<String> = mutableListOf()
                for (i in 0 until T) {
                    db.collection("Notes").document(ids_documentos[i]).get()
                        .addOnSuccessListener { note ->
                            val userIDS = note.get("UserID") as ArrayList<String>
                            for (a in userIDS) {
                                if (a == currentUserId) {
                                    ids_documentos_final.add(ids_documentos[i])
                                    names.add(note.get("Name") as String)
                                    colors.add(note.get("Color") as String)
                                    subtitles.add(note.get("Subtitle") as String)
                                    products.add(note.get("Products") as ArrayList<String>)
                                    amounts.add(note.get("Amount") as ArrayList<Int>)
                                    if (note.get("Checks") !== null) {
                                        checks.add(note.get("Checks") as ArrayList<Boolean>)
                                    }
                                    ids.add(note.get("UserID") as ArrayList<String>)
                                    rv.apply {
                                        setHasFixedSize(true)
                                        layoutManager = LinearLayoutManager(this@MainActivity)
                                        adapter = NoteAdapter(
                                            this@MainActivity,
                                            names,
                                            subtitles,
                                            colors,
                                            products,
                                            amounts,
                                            checks,
                                            i,
                                            ids_documentos_final,
                                            ids
                                        )
                                    }
                                }
                            }
                        }
                }
            }
        }
        load()
        swipeRefresh.setOnRefreshListener {
            load()
            swipeRefresh.isRefreshing = false
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.addnote ->{
                val intent = Intent(this, ShareActivity::class.java)
                startActivity(intent)
            }
            R.id.delete ->{
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