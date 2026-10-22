package br.unisanta.myapplication.repository

import br.unisanta.myapplication.model.Movie

class MovieRepository {

    private var nextId = 4L

    private val movies = mutableListOf(
        Movie(1, "Blade Runner: O Caçador de Androides", 5f, "https://m.media-amazon.com/images/S/pv-target-images/04e3cc0273ea705427b8497299c1a3d2e7faa9af0821a9c77e18902cd087f2b7.jpg"),
        Movie(2, "Alien: O Oitavo Passageiro", 5f, "https://m.media-amazon.com/images/S/pv-target-images/db8c53764be417e7678d869ecb6bf64a0f7fe941ae0ad03f574a089f0d308b8b.jpg"),
        Movie(4, "Cidadão Kane", 5f, "https://m.media-amazon.com/images/S/pv-target-images/c9e4736d3370c1fdc4e326fea255861e57943dafc318e0c5bbb3e645ac28cf6f.jpg"),
    )

    fun getAll(): List<Movie> = movies.toList()

    fun add(name: String, rating: Float, imageUrl: String) {
        movies.add(Movie(nextId++, name, rating, imageUrl))
    }

    fun update(movie: Movie) {
        val index = movies.indexOfFirst { it.id == movie.id }
        if (index != -1) movies[index] = movie
    }

    fun delete(id: Long) {
        movies.removeAll { it.id == id }
    }
}