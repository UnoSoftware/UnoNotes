package com.astra.notes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginbtn: Button
    private lateinit var signupredirect: TextView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        email = findViewById(R.id.Email)
        password = findViewById(R.id.password)
        loginbtn = findViewById(R.id.loginbtn)
        signupredirect = findViewById(R.id.noaccount)
        auth = Firebase.auth

        loginbtn.setOnClickListener {
            if (email.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    load(user)
                }
                else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

        //Actualizar cuando esté la página de registro
        signupredirect.setOnClickListener {
            //val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    //Ver si alguien ya tiene la sesión iniciada
    public override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            load(user);
        }
    }

    private fun load(user: FirebaseUser?) {
        Toast.makeText(this, "Authentication successful", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}