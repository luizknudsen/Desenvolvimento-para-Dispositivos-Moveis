package com.example.myapplication

data class Tarefa(
    var tarefa: String,
    var horario: String,
    var descricao: String,
    var concluida: Boolean = false
)