package com.example.clubdeportivo

import android.os.Bundle
import android.text.Selection
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: BDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets


        dbHelper = BDatos(this)

        val success = dbHelper.agregarUsuariosEjemplo()
        if (success) {
            Toast.makeText(this, "Usuarios creados con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No se pudieron crear los usuarios de prueba en la DB", Toast.LENGTH_SHORT).show()
        }

        val success2 = dbHelper.agregarSociosEjemplo()
        if (success2) {
            Toast.makeText(this, "Socios creados con éxito", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No se pudieron crear los socios en la DB", Toast.LENGTH_SHORT).show()
        }



        val btnIngresarApp = findViewById<Button>(R.id.btnIngresarApp)
        btnIngresarApp.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

           //llamemos a agregarUsuariosEjemplo


        }

    }
}