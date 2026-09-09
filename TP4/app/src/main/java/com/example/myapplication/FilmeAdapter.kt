package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmeAdapter(
    var filmes: MutableList<Filme>
) : RecyclerView.Adapter<FilmeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmeViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filme, parent, false)

        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FilmeViewHolder,
        position: Int
    ) {

        holder.Bind(filmes[position])
    }

    override fun getItemCount(): Int {

        return filmes.size
    }

    fun AdicionarFilme(filme: Filme) {

        filmes.add(filme)
        notifyItemInserted(filmes.size - 1)
    }
}
