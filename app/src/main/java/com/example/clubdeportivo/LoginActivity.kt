package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: BDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        dbHelper = BDatos(this)

        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener{
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()




            if (authenticateUser(username, password)) {
                Toast.makeText(this, "Login successful", LENGTH_SHORT).show()

                // Crear un Intent para abrir la MenuActivity
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid username or password", LENGTH_SHORT).show()
            }

        }

    }

    private fun authenticateUser(username: String, password: String): Boolean {
        val usuarios = dbHelper.getAllUsuarios()
        for (usuario in usuarios) {
            if (usuario.nombreUsuario == username && usuario.password == password) {
                return true
            }
        }
        return false
    }
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}