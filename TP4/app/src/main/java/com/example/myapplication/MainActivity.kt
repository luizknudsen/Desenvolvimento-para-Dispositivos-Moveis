package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Filme
import com.example.myapplication.FilmeAdapter
import com.example.myapplication.RegisterFilmesActivity
import com.example.myapplication.RegisterTarefasActivity
import com.example.myapplication.Tarefa
import com.example.myapplication.TarefaAdapter

class MainActivity : AppCompatActivity() {

    lateinit var txt_titulo: TextView
    lateinit var bt_registrarfilme: TextView
    lateinit var bt_registartarefa: TextView
    lateinit var txt_tarefas: TextView

    lateinit var recycler_view_filmes: RecyclerView
    lateinit var recycler_view_tarefas: RecyclerView

    lateinit var filmeAdapter: FilmeAdapter
    lateinit var tarefaAdapter: TarefaAdapter

    var filmes = mutableListOf<Filme>()
    var tarefas = mutableListOf<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Components()
        ConfigurarRecyclerViews()
    }

    fun Components() {

        txt_titulo = findViewById(R.id.txt_titulo)
        bt_registrarfilme = findViewById(R.id.bt_registrarfilme)
        bt_registartarefa = findViewById(R.id.bt_registartarefa)
        txt_tarefas = findViewById(R.id.txt_tarefas)

        recycler_view_filmes = findViewById(R.id.recycler_view_filmes)
        recycler_view_tarefas = findViewById(R.id.recycler_view_tarefas)

        bt_registrarfilme.setOnClickListener {
            onClick(it as TextView)
        }

        bt_registartarefa.setOnClickListener {
            onClick(it as TextView)
        }
    }

    fun onClick(view: TextView) {

        if (view.id == R.id.bt_registrarfilme) {

            val intent = Intent(this, RegisterFilmesActivity::class.java)

            startActivityForResult(
                intent,
                1
            )

        } else if (view.id == R.id.bt_registartarefa) {

            val intent = Intent(this, RegisterTarefasActivity::class.java)

            startActivityForResult(
                intent,
                2
            )
        }
    }

    fun ConfigurarRecyclerViews() {

        filmeAdapter = FilmeAdapter(filmes)
        tarefaAdapter = TarefaAdapter(tarefas)

        recycler_view_filmes.layoutManager =
            GridLayoutManager(this, 2)

        recycler_view_filmes.adapter =
            filmeAdapter

        recycler_view_tarefas.layoutManager =
            LinearLayoutManager(this)

        recycler_view_tarefas.adapter =
            tarefaAdapter

        val espaco = (5 * resources.displayMetrics.density).toInt()

        recycler_view_filmes.addItemDecoration(
            EspacoItemDecoration(espaco)
        )

        recycler_view_tarefas.addItemDecoration(
            EspacoItemDecoration(espaco)
        )
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (resultCode == Activity.RESULT_OK && data != null) {

            if (requestCode == 1) {

                val titulo =
                    data.getStringExtra("titulo")

                val diretor =
                    data.getStringExtra("diretor")

                if (titulo != null && diretor != null) {

                    val filme = Filme(
                        titulo,
                        diretor
                    )

                    filmeAdapter.AdicionarFilme(filme)
                }

            } else if (requestCode == 2) {

                val tarefa =
                    data.getStringExtra("tarefa")

                val horario =
                    data.getStringExtra("horario")

                val descricao =
                    data.getStringExtra("descricao")

                if (tarefa != null &&
                    horario != null &&
                    descricao != null
                ) {

                    val novaTarefa = Tarefa(
                        tarefa,
                        horario,
                        descricao
                    )

                    tarefaAdapter.AdicionarTarefa(novaTarefa)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        filmeAdapter.notifyDataSetChanged()
        tarefaAdapter.notifyDataSetChanged()
    }
}
