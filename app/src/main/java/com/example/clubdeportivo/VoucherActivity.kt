package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VoucherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_voucher)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}

        val btnVoucherMenu = findViewById<Button>(R.id.btnVoucherMenu)
        btnVoucherMenu.setOnClickListener{
            // Crear un Intent para abrir la MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        //val btnVoucherPrint = findViewById<Button>(R.id.btnVoucherPrint)
        //btnVoucherPrint.setOnClickListener{
            // Acción para imprimir
        //}
    }
}