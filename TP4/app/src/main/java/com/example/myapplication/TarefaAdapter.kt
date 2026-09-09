package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TarefaAdapter(
    var tarefas: MutableList<Tarefa>
) : RecyclerView.Adapter<TarefaViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TarefaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa, parent, false)

        return TarefaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TarefaViewHolder,
        position: Int
    ) {

        holder.Bind(tarefas[position]) {

            tarefas[position].concluida = true

            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {

        return tarefas.size
    }

    fun AdicionarTarefa(tarefa: Tarefa) {

        tarefas.add(tarefa)
        notifyItemInserted(tarefas.size - 1)
    }
}
