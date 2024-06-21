package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}

        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener{
            // Crear un Intent para abrir la MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val btnAccept = findViewById<Button>(R.id.btnAccept)
        btnAccept.setOnClickListener{
            // Crear un Intent para abrir la InformationActivity
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }
    }
}