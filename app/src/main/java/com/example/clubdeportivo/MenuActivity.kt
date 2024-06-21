package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets

        val btnListarVencimientos = findViewById<Button>(R.id.btnListarVencimientos)
        btnListarVencimientos.setOnClickListener{
            // Crear un Intent para abrir la ListActivity
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        btnConsultar.setOnClickListener{
            // Crear un Intent para abrir la InformationActivity
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        val btnRegisterClients = findViewById<Button>(R.id.btnRegisterClients)
        btnRegisterClients.setOnClickListener{
            // Crear un Intent para abrir la RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnRegisterPayment = findViewById<Button>(R.id.btnRegisterPayment)
        btnRegisterPayment.setOnClickListener{
            // Crear un Intent para abrir la PaymentActivity
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }
}