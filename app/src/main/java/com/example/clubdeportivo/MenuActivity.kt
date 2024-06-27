package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MenuActivity : AppCompatActivity() {

    private lateinit var dbHelper: BDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        dbHelper = BDatos(this)

        val btnListarVencimientos = findViewById<Button>(R.id.btnListarVencimientos)
        btnListarVencimientos.setOnClickListener{
            // Crear un Intent para abrir la ListActivity
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

       /* val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        btnConsultar.setOnClickListener{
            // Crear un Intent para abrir la InformationActivity
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }*/

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



        val editTextDNI = findViewById<TextInputEditText>(R.id.editTextDNI)
        val btnConsultarMenu = findViewById<Button>(R.id.btnConsultarMenu)
        //val textViewResult = findViewById<TextView>(R.id.textViewResult) // Asegúrate de tener este TextView en tu layout


        btnConsultarMenu.setOnClickListener {
            val dni = editTextDNI.text.toString()

            if (dni.isNotEmpty()) {
                val socio = dbHelper.getSocioByDocumento(dni)
                if (socio != null) {
                    // Iniciar CarnetActivity y pasar los datos del usuario
                    val intent = Intent(this, CarnetActivity::class.java).apply {
                        putExtra("nombre", socio.cnombre)
                        putExtra("apellido", socio.apellido)
                        putExtra("domicilio", socio.domicilio)
                        putExtra("aptofisico", socio.aptofisico)
                        putExtra("fechanacimiento", socio.fechanacimiento)
                        putExtra("email", socio.email)
                        putExtra("documento", dni)
                    }
                    startActivity(intent)
                } else {
                    //textViewResult.text = "Usuario no encontrado."
                    Toast.makeText(this, "Usuario no encontrado.", Toast.LENGTH_SHORT).show()
                }
            } else {
                editTextDNI.error = "El DNI es obligatorio"
            }
        }

    }
}