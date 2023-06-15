package com.tuna.kotlininstagram.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuna.kotlininstagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)
        auth= Firebase.auth
        val currentUser=auth.currentUser
        if(currentUser!=null){
            val intent=Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun signin(view:View){
        val email=binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()
        if(email.equals("")||password.equals("")){
        Toast.makeText(this@MainActivity," Enter email and password",Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent=Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }

    fun signup(view: View){
        val email=binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()
        if(email.equals("")||password.equals("")){
            Toast.makeText(this,"enter email and password",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                //success
                val intent=Intent(this@MainActivity, FeedActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

    }
}