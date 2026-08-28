package br.unisanta.appdao.dao

import br.unisanta.appdao.model.Aluno

class AlunoDao {

    companion object {
        private var aluno: Aluno? = null
    }

    fun salvar(nome: String, turma: String) {
        aluno = Aluno(nome, turma)
    }

    fun buscar(): Aluno? {
        return aluno
    }
}