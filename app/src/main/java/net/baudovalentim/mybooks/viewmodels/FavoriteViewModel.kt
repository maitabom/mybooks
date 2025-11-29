package net.baudovalentim.mybooks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.baudovalentim.mybooks.entity.BookEntity
import net.baudovalentim.mybooks.repository.BookRepository

class FavoriteViewModel : ViewModel() {
    private val _books = MutableLiveData<List<BookEntity>>()
    private val repository = BookRepository.getInstance()

    val books: LiveData<List<BookEntity>> = _books

    fun getFavoriteBooks() {
        _books.value = repository.getFavoriteBooks()
    }

    fun favorite(id: Int) {
        repository.toggleFavorite(id)
    }
}