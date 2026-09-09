package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RegisterTarefasActivity : AppCompatActivity() {

    lateinit var bt_return: ImageView
    lateinit var bt_send: ImageView

    lateinit var edit_tarefa: EditText
    lateinit var edit_horario: EditText
    lateinit var edit_descricao: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registertarefas)

        Components()
    }

    fun Components() {

        bt_return = findViewById(R.id.bt_return)
        bt_send = findViewById(R.id.bt_send)

        edit_tarefa = findViewById(R.id.edit_tarefa)
        edit_horario = findViewById(R.id.edit_horario)
        edit_descricao = findViewById(R.id.edit_descricao)

        bt_return.setOnClickListener {
            onClick(it as ImageView)
        }

        bt_send.setOnClickListener {
            onClick(it as ImageView)
        }
    }

    fun onClick(view: ImageView) {

        if (view.id == R.id.bt_return) {

            finish()

        } else if (view.id == R.id.bt_send) {

            val tarefa = edit_tarefa.text.toString()
            val horario = edit_horario.text.toString()
            val descricao = edit_descricao.text.toString()

            if (tarefa.isNotEmpty() &&
                horario.isNotEmpty() &&
                descricao.isNotEmpty()
            ) {

                val intent = Intent()

                intent.putExtra("tarefa", tarefa)
                intent.putExtra("horario", horario)
                intent.putExtra("descricao", descricao)

                setResult(Activity.RESULT_OK, intent)

                finish()
            }
        }
    }
}
