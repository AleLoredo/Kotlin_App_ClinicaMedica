package com.example.clubdeportivo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate

class PayActivity : AppCompatActivity() {

    private lateinit var dbHelper: BDatos
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pay)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        dbHelper = BDatos(this)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val documento = intent.getStringExtra("documento")
        val flagsocio = intent.getStringExtra("essocio")

        val textViewNombre = findViewById<TextView>(R.id.textViewNombrePay)
        val textViewApellido = findViewById<TextView>(R.id.textViewApellidoPay)

        val etMonto = findViewById<EditText>(R.id.inputMonto)
        val etFechaVenc = findViewById<EditText>(R.id.inputFechaVenc)

        textViewNombre.text = "$nombre"
        textViewApellido.text = "$apellido"

        val btnCancelPayActivity = findViewById<Button>(R.id.btnCancelPay)
        btnCancelPayActivity.setOnClickListener{
            // Crear un Intent para abrir la MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }


        //copy init
        val btnAcceptPayActivity = findViewById<Button>(R.id.btnAcceptPay)

        btnAcceptPayActivity.setOnClickListener{
            //guardo la informacion


            val fechaCobro : LocalDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDate.now()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            val fechaVenc = etFechaVenc.text.toString()
            val monto = etMonto.text.toString()

            if (flagsocio == "1") {
                    dbHelper.registrarPagosSocio(documento.toString(), fechaCobro, fechaVenc, monto)
                    Toast.makeText(this, "Se registro pago del socio", Toast.LENGTH_SHORT).show()
                }
            else if (flagsocio == "0")  {
                    dbHelper.registrarPagosNoSocio(documento.toString(), fechaCobro, fechaVenc, monto)
                    Toast.makeText(this, "Se registro pago del No socio", Toast.LENGTH_SHORT).show()
                }
            else {
                Toast.makeText(this, "Error: flagsocio no evalua 0 ni 1", Toast.LENGTH_SHORT).show()
            }

            // Crear un Intent para abrir la InformationActivity
            val intent = Intent(this, VoucherActivity::class.java)
            startActivity(intent)
        }


        //copy fin




    }
}