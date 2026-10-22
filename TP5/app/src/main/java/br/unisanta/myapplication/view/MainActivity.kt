package br.unisanta.myapplication.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.unisanta.myapplication.databinding.ActivityMainBinding
import br.unisanta.myapplication.databinding.DialogMovieBinding
import br.unisanta.myapplication.model.Movie
import br.unisanta.myapplication.viewmodel.MovieViewModel
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieAdapter(
            onEdit = { movie -> showMovieDialog(movie) },
            onDelete = { movie -> confirmDelete(movie) }
        )

        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter

        viewModel.movies.observe(this) { list ->
            adapter.submitList(list)
        }

        binding.fabAdd.setOnClickListener { showMovieDialog(null) }
    }

    // se o movie for null, adicionar | movie for diferente de  null, editar
    private fun showMovieDialog(movie: Movie?) {
        val dialogBinding = DialogMovieBinding.inflate(layoutInflater)

        movie?.let {
            dialogBinding.etName.setText(it.name)
            dialogBinding.ratingBarEdit.rating = it.rating
            dialogBinding.etImageUrl.setText(it.imageUrl)
            loadPreview(dialogBinding, it.imageUrl)
        }

        dialogBinding.btnPreview.setOnClickListener {
            loadPreview(dialogBinding, dialogBinding.etImageUrl.text.toString().trim())
        }

        AlertDialog.Builder(this)
            .setTitle(if (movie == null) "Novo filme" else "Editar filme")
            .setView(dialogBinding.root)
            .setPositiveButton("Salvar") { _, _ ->
                val name = dialogBinding.etName.text.toString().trim()
                val rating = dialogBinding.ratingBarEdit.rating
                val url = dialogBinding.etImageUrl.text.toString().trim()

                if (name.isEmpty()) {
                    Toast.makeText(this, "Informe o nome do filme", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                if (movie == null) {
                    viewModel.addMovie(name, rating, url)
                } else {
                    viewModel.updateMovie(movie.copy(name = name, rating = rating, imageUrl = url))
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun loadPreview(dialogBinding: DialogMovieBinding, url: String) {
        dialogBinding.ivPreview.load(url) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
            error(android.R.drawable.ic_menu_report_image)
        }
    }

    private fun confirmDelete(movie: Movie) {
        AlertDialog.Builder(this)
            .setTitle("Excluir filme")
            .setMessage("Deseja excluir \"${movie.name}\"?")
            .setPositiveButton("Excluir") { _, _ -> viewModel.deleteMovie(movie.id) }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}