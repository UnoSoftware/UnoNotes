package com.astra.notes

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_notes_view.*
import kotlinx.android.synthetic.main.activity_notes_view.subtitle_tv
import kotlinx.android.synthetic.main.activity_notes_view.title_tv
import kotlinx.android.synthetic.main.product_layout.*

class NotesViewActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private val auth = FirebaseAuth.getInstance()

    lateinit var noteName: String
    lateinit var noteSubtitle: String
    lateinit var id: String
    lateinit var color: String
    lateinit var userID: String
    lateinit var products: ArrayList<String>
    lateinit var amounts: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_view)

        var view = findViewById<View>(R.id.LayoutNoteView)
        var viewImageDark = findViewById<View>(R.id.imageView4)
        var viewImageLight = findViewById<View>(R.id.imageView5)

        if(MainActivity.globalDark){
            view.setBackgroundColor(Color.parseColor("#FF000000"))
            viewImageLight.setVisibility(View.GONE)
            viewImageDark.setVisibility(View.VISIBLE)
        }else{
            view.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
            viewImageLight.setVisibility(View.VISIBLE)
            viewImageDark.setVisibility(View.GONE)
        }

        val extras = intent.extras
        noteName = extras!!.getString("name").toString()
        noteSubtitle = extras?.getString("subtitle").toString()
        id = extras?.getString("id").toString()
        color = extras?.getString("color").toString()
        userID = extras?.getString("iduser").toString()
        products = extras?.get("products") as ArrayList<String>
        amounts = extras?.get("amounts") as ArrayList<Int>
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

        imageView5.setOnClickListener {
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
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }
        }

        delete_prod.setOnClickListener {
            // Eliminar el producto que el usuario haya seleccionado, y guardar los cambios en la base de datos
            products.removeAt(prod_rv.getChildAdapterPosition(prod_rv.getChildAt(0)))
            amounts.removeAt(prod_rv.getChildAdapterPosition(prod_rv.getChildAt(0)))
            val intent = Intent(this, NotesViewActivity::class.java)
            intent.putExtra("name", noteName)
            intent.putExtra("subtitle", noteSubtitle)
            intent.putExtra("products", products)
            intent.putExtra("amounts", amounts)
            intent.putExtra("color", color)
            intent.putExtra("id", id)
            intent.putExtra("iduser", userID)
            startActivity(intent)
            // Actualizar la base de datos
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
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.delete ->{
                db.collection("Notes").document(id).delete().
                addOnSuccessListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                    .addOnFailureListener{
                        Utils.showError(this, it.message.toString())
                    }
            }
            R.id.change_color ->{
                val intent = Intent(this, ChangeColorActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", noteName)
                intent.putExtra("color", color)
                intent.putExtra("subtitle", noteSubtitle)
                intent.putExtra("products", products)
                intent.putExtra("amounts", amounts)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
