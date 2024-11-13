package com.example.mynote

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class read:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        val back:TextView = findViewById(R.id.back)
        back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        val textmain:TextView = findViewById(R.id.textmain)
        val textdesc:TextView = findViewById(R.id.textdesc)
        val id = intent.getStringExtra("id").toString()
        val db = Firebase.firestore
        db.collection("notes").document(id).get().addOnSuccessListener {
            textmain.text = it.getString("name").toString()
            textdesc.text = it.getString("desctext").toString()
        }.addOnFailureListener {
            Toast.makeText(this, "Не удалось получить данные.", Toast.LENGTH_SHORT).show()
        }
    }
}