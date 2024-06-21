package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}
        val btnCardStart = findViewById<Button>(R.id.btnCardStart)
        btnCardStart.setOnClickListener{
            // Crear un Intent para abrir la MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        //val btnCardPrint = findViewById<Button>(R.id.btnCardPrint)
        //btnCardStart.setOnClickListener{
            // aqui se realizará la acción de imprimir
        //}
    }
}