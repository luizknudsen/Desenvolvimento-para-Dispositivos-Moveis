package br.unisanta.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.unisanta.myapplication.model.Movie
import br.unisanta.myapplication.repository.MovieRepository

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository();

    private val _movies = MutableLiveData(repository.getAll());
    val movies: LiveData<List<Movie>> = _movies;

    private fun refresh() {
        _movies.value = repository.getAll();
    }

    fun addMovie(name: String, rating: Float, imageUrl: String) {
        repository.add(name, rating, imageUrl);
        refresh();
    }

    fun updateMovie(movie: Movie) {
        repository.update(movie);
        refresh();
    }

    fun deleteMovie(id: Long) {
        repository.delete(id);
        refresh();
    }
}