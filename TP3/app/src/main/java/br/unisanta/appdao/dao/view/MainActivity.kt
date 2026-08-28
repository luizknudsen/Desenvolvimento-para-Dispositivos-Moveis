package br.unisanta.appdao.dao.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appdao.dao.R

class MainActivity : AppCompatActivity() {

    private lateinit var btAluno: TextView
    private lateinit var btImc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btAluno = findViewById(R.id.bt_aluno)
        btImc = findViewById(R.id.bt_imc)

        btAluno.setOnClickListener {
            val intent = Intent(this, AlunoActivity::class.java)
            startActivity(intent)
        }

        btImc.setOnClickListener {
            val intent = Intent(this, IMCActivity::class.java)
            startActivity(intent)
        }
    }
}