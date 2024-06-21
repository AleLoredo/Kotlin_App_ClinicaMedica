package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_information)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}
        val btnRegPayment = findViewById<Button>(R.id.btnRegPayment)
        btnRegPayment.setOnClickListener{
            // Crear un Intent para abrir la PaymentActivity
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        val btnVerCarnet = findViewById<Button>(R.id.btnVerCarnet)
        btnVerCarnet.setOnClickListener{
            // Crear un Intent para abrir la CardActivity
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
        }
    }
}