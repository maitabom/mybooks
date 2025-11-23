package net.baudovalentim.mybooks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.baudovalentim.mybooks.entity.BookEntity
import net.baudovalentim.mybooks.repository.BookRepository

class DetailsViewModel : ViewModel() {
    private val repository: BookRepository = BookRepository()
    private val _book = MutableLiveData<BookEntity>()
    val book: LiveData<BookEntity> = _book

    fun getBookById(bookId: Int) {
        _book.value = repository.getBookById(bookId)
    }
}