package br.unisanta.appdao.dao.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import br.unisanta.appdao.dao.R;

class IMCResultadoActivity : AppCompatActivity() {

    private lateinit var txtImc: TextView
    private lateinit var txtClassificacao: TextView
    private lateinit var btReturn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_resultado)

        txtImc = findViewById(R.id.txt_imc)
        txtClassificacao = findViewById(R.id.txt_classificacao)
        btReturn = findViewById(R.id.bt_return)

        val imc = intent.getDoubleExtra("IMC", 0.0)

        txtImc.text = "IMC: %.2f".format(imc)

        val classificacao = when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Peso ideal"
            imc < 30.0 -> "Sobrepeso"
            else -> "Obesidade"
        }

        txtClassificacao.text = classificacao

        btReturn.setOnClickListener {
            finish()
        }
    }
}