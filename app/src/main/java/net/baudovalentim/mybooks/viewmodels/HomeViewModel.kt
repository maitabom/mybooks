package net.baudovalentim.mybooks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.baudovalentim.mybooks.entity.BookEntity
import net.baudovalentim.mybooks.repository.BookRepository

class HomeViewModel : ViewModel() {
    private val _books = MutableLiveData<List<BookEntity>>()
    private val repository = BookRepository.getInstance()

    val books: LiveData<List<BookEntity>> = _books

    fun getAllBooks() {
        _books.value = repository.getAllBooks()
    }

    fun favorite(id: Int) {

    }
}