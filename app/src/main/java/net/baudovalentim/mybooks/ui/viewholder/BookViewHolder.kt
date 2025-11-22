package net.baudovalentim.mybooks.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import net.baudovalentim.mybooks.databinding.ItemBookBinding
import net.baudovalentim.mybooks.entity.BookEntity

class BookViewHolder(private val item: ItemBookBinding): RecyclerView.ViewHolder(item.root) {
    fun bind(book: BookEntity) {
        item.tvTitle.text = book.title
    }
}