package br.unisanta.appdao.dao.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appdao.dao.R

class IMCActivity : AppCompatActivity() {

    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var btCalcular: TextView
    private lateinit var btReturn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editPeso = findViewById(R.id.edit_peso)
        editAltura = findViewById(R.id.edit_altura)
        btCalcular = findViewById(R.id.bt_calcular)
        btReturn = findViewById(R.id.bt_return)

        btCalcular.setOnClickListener {

            val pesoTexto = editPeso.text.toString()
            val alturaTexto = editAltura.text.toString()

            if (pesoTexto.isEmpty() || alturaTexto.isEmpty()) {
                Toast.makeText(
                    this,
                    "Preencha o peso e a altura",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val peso = pesoTexto.replace(",", ".").toDouble()
            val altura = alturaTexto.replace(",", ".").toDouble()

            if (peso <= 0 || altura <= 0) {
                Toast.makeText(
                    this,
                    "Digite valores maiores que zero",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val imc = peso / (altura * altura)

            val intent = Intent(this, IMCResultadoActivity::class.java)

            intent.putExtra("IMC", imc)

            startActivity(intent)
        }

        btReturn.setOnClickListener {
            finish()
        }
    }
}