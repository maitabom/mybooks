package net.baudovalentim.mybooks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.baudovalentim.mybooks.entity.BookEntity
import net.baudovalentim.mybooks.repository.BookRepository

class DetailsViewModel : ViewModel() {
    private val repository: BookRepository = BookRepository()
    private val _book = MutableLiveData<BookEntity>()
    private val _bookRemoval = MutableLiveData<Boolean>()
    val book: LiveData<BookEntity> = _book
    val bookRemoval: LiveData<Boolean> = _bookRemoval

    fun getBookById(bookId: Int) {
        _book.value = repository.getBookById(bookId)
    }

    fun deleteBook(bookId: Int) {
        _bookRemoval.value = repository.delete(bookId)
    }

    fun toggleFavorite(bookId: Int) {
        repository.toggleFavorite(bookId)
    }
}