package com.example.mynote

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class addreport : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addreport)
        val back: TextView = findViewById(R.id.back)
        back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        val db = Firebase.firestore
        val editextmain:EditText = findViewById(R.id.editextmain)
        val editextdesc:EditText = findViewById(R.id.editextdesc)
        val save:Button = findViewById(R.id.save)
        editextmain.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT || (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.repeatCount == 0)){
                editextdesc.requestFocus()
                true
            }
            else{
                false
            }
        }
        save.setOnClickListener {
            if(editextmain.text.isNotEmpty() && editextdesc.text.isNotEmpty()){
                val report = hashMapOf(
                "date" to 20240513,
                "desctext" to editextdesc.text.toString(),
                "name" to editextmain.text.toString()
                )
            db.collection("notes")
                .add(report)
                .addOnSuccessListener { documentReference ->
                    val h = Handler()
                        it.postDelayed({
                            startActivity(Intent(this, MainActivity::class.java))
                        }, 1000)
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Не удалось добавить!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }

            }
            else{
                Toast.makeText(this, "Заполнены не все поля!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
