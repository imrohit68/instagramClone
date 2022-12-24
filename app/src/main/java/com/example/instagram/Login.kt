package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.instagram.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val firebaseAuth = FirebaseAuth.getInstance()


        binding.signupActivity.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }
        binding.Login.setOnClickListener {

            binding.Login.isEnabled = false
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (email != null && password != null) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    binding.Login.isEnabled = true

                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please Enter Your Email and Password!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}