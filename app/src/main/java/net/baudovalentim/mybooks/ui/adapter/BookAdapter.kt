package net.baudovalentim.mybooks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.baudovalentim.mybooks.databinding.ItemBookBinding
import net.baudovalentim.mybooks.entity.BookEntity
import net.baudovalentim.mybooks.ui.listeners.BookListener
import net.baudovalentim.mybooks.ui.viewholder.BookViewHolder

class BookAdapter : RecyclerView.Adapter<BookViewHolder>() {
    private var bookList: List<BookEntity> = listOf()
    private  lateinit var bookListener: BookListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val view = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(view, bookListener)
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int
    ) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun updateBooks(list: List<BookEntity>) {
        bookList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: BookListener) {
        bookListener = listener
    }
}