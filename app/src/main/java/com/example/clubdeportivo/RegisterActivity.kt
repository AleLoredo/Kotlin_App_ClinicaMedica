package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    private lateinit var dbHelper: BDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}
        dbHelper = BDatos(this)

        val etDocumento = findViewById<EditText>(R.id.inputDocumento)
        val etNombre = findViewById<EditText>(R.id.inputNombre)
        val etApellido = findViewById<EditText>(R.id.inputApellido)
        val etFechaNacimiento = findViewById<EditText>(R.id.inputFechaNacimiento)
        val etDomicilio = findViewById<EditText>(R.id.inputDomicilio)
        val etEmail = findViewById<EditText>(R.id.inputEmail)
        val etRadioGroupTipo = findViewById<RadioGroup>(R.id.radioGroupTipo)
        val etAptoFisico = findViewById<EditText>(R.id.inputAptoFisico)

        val btnAccept = findViewById<Button>(R.id.btnAccept)

        btnAccept.setOnClickListener{
            //guardo la informacion
            val documento = etDocumento.text.toString()
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val fechanacimiento = etFechaNacimiento.text.toString()
            val domicilio = etDomicilio.text.toString()
            val email = etEmail.text.toString()
            val aptoFisico = etAptoFisico.text.toString()
            val selectedRadioButtonId = etRadioGroupTipo.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val isSocio = findViewById<RadioButton>(selectedRadioButtonId).text == "Socio"

                if (isSocio) {
                    dbHelper.insertSocio(documento, nombre, apellido, fechanacimiento, domicilio, email, aptoFisico )
                    Toast.makeText(this, "Socio registrado con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    dbHelper.insertNoSocio(documento, nombre, apellido, fechanacimiento, domicilio, email)
                    Toast.makeText(this, "No socio registrado con éxito", Toast.LENGTH_SHORT).show()
                }
                finish()
            } else {
                Toast.makeText(this, "Por favor, selecciona un tipo de usuario", Toast.LENGTH_SHORT).show()
            }

        // Crear un Intent para abrir la InformationActivity
        val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }

        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener{
            // Crear un Intent para abrir la MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}