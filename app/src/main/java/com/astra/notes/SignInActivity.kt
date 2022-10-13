package com.astra.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class SignInActivity : AppCompatActivity() {

    var email: EditText = findViewById(R.id.Email)
    private var password: EditText = findViewById(R.id.password)
    var loginbtn: Button = findViewById(R.id.loginbtn)
    var signupredirect: TextView = findViewById(R.id.noaccount)
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        //Actualizar cuando esté la página de registro
        signupredirect.setOnClickListener {
            //val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    //Ver si alguien ya tiene la sesión iniciada
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

    private fun reload() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}