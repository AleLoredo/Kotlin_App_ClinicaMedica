package com.example.clubdeportivo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //    insets
        //}

        val btnCancelPay = findViewById<Button>(R.id.btnCancelPay)
        btnCancelPay.setOnClickListener{
            // Crear un Intent para abrir la MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        val btnAcceptPey = findViewById<Button>(R.id.btnAcceptPey)
        btnAcceptPey.setOnClickListener{
            // Crear un Intent para abrir la VoucherActivity
            val intent = Intent(this, VoucherActivity::class.java)
            startActivity(intent)
        }
    }
}