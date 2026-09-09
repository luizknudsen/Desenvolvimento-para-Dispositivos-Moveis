package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var txt_filme: TextView
    lateinit var txt_diretor: TextView

    fun Components() {

        txt_filme = itemView.findViewById(R.id.txt_filme)
        txt_diretor = itemView.findViewById(R.id.txt_diretor)
    }

    fun Bind(filme: Filme) {

        Components()

        txt_filme.text = filme.titulo
        txt_diretor.text = filme.diretor
    }
}
