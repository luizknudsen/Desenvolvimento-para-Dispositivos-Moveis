package com.example.myapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TarefaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var txt_tarefa: TextView
    lateinit var txt_horario: TextView
    lateinit var txt_descricao: TextView

    fun Components() {

        txt_tarefa = itemView.findViewById(R.id.txt_tarefa)
        txt_horario = itemView.findViewById(R.id.txt_horario)
        txt_descricao = itemView.findViewById(R.id.txt_descricao)
    }

    fun Bind(tarefa: Tarefa, onConcluir: () -> Unit) {

        Components()

        txt_tarefa.text = tarefa.tarefa
        txt_horario.text = tarefa.horario
        txt_descricao.text = tarefa.descricao

        if (tarefa.concluida) {
            txt_tarefa.alpha = 0.5f
            txt_horario.alpha = 0.5f
            txt_descricao.alpha = 0.5f
        } else {
            txt_tarefa.alpha = 1.0f
            txt_horario.alpha = 1.0f
            txt_descricao.alpha = 1.0f
        }
    }
}
