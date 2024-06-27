package com.example.clubdeportivo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarnetActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carnet)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val fechanacimiento = intent.getStringExtra("fechanacimiento")
        val documento = intent.getStringExtra("documento")


        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        val textViewApellido = findViewById<TextView>(R.id.textViewApellido)
        val textViewFechaNacimiento = findViewById<TextView>(R.id.textViewFechaNacimiento)
        val textViewDocumento = findViewById<TextView>(R.id.textViewDocumento)

        /*
        textViewNombre.text = "Nombre: $nombre"
        textViewApellido.text = "Apellido: $apellido"
        textViewFechaNacimiento.text = "Fecha de nacimiento: $fechanacimiento"
        textViewDocumento.text = "Documento: $documento"
         */

        textViewNombre.text = "$nombre"
        textViewApellido.text = "$apellido"
        textViewFechaNacimiento.text = "$fechanacimiento"
        textViewDocumento.text = "$documento"

    }
}