package br.unisanta.appdao.dao.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appdao.dao.AlunoDao
import br.unisanta.appdao.dao.R

class AlunoActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var btEnviar: TextView
    private lateinit var btReturn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluno)

        editNome = findViewById(R.id.edit_nome)
        btEnviar = findViewById(R.id.bt_enviar)
        btReturn = findViewById(R.id.bt_return)

        btEnviar.setOnClickListener {

            val nome = editNome.text.toString()

            if (nome.isEmpty()) {
                Toast.makeText(
                    this,
                    "Digite o nome do aluno",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val alunoDao = AlunoDao()

            alunoDao.salvar(nome, "")

            val intent = Intent(this, AlunoResultadoActivity::class.java)

            startActivity(intent)
        }

        btReturn.setOnClickListener {
            finish()
        }
    }
}