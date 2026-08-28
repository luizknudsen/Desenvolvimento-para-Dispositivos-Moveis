package br.unisanta.appdao.dao.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.appdao.dao.AlunoDao
import br.unisanta.appdao.dao.R
import kotlin.random.Random

class AlunoResultadoActivity : AppCompatActivity() {

    private lateinit var txtNome: TextView
    private lateinit var txtMatricula: TextView
    private lateinit var btReturn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aluno_resultado)

        txtNome = findViewById(R.id.txt_nome)
        txtMatricula = findViewById(R.id.txt_matricula)
        btReturn = findViewById(R.id.bt_return)

        val alunoDao = AlunoDao()
        val aluno = alunoDao.buscar()

        if (aluno != null) {

            txtNome.text = "Aluno: ${aluno.nome}"

            val matricula = Random.nextInt(100000, 999999)

            txtMatricula.text = "Matrícula: $matricula"
        }

        btReturn.setOnClickListener {
            finish()
        }
    }
}