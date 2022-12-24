package com.example.instagram

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.databinding.ActivitySignupBinding
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class signup : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firebaseAuth = FirebaseAuth.getInstance()
        val firebaseStore = FirebaseFirestore.getInstance()


        binding.login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }
        binding.signup.setOnClickListener {

            binding.signup.isEnabled = false

            val email = binding.email.text.toString()
            val fullName = binding.fullname.text.toString()
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (email.isBlank() || fullName.isBlank() || username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {

                    binding.signup.isEnabled = true

                    if (it.isSuccessful) {
                        firebaseStore.collection("User")
                            .document(FirebaseAuth.getInstance().uid.toString())
                            .set(UserModel(email, username, fullName))
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}