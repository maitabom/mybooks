package net.baudovalentim.mybooks.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import net.baudovalentim.mybooks.R
import net.baudovalentim.mybooks.databinding.ItemBookBinding
import net.baudovalentim.mybooks.entity.BookEntity
import net.baudovalentim.mybooks.ui.listeners.BookListener

class BookViewHolder(private val item: ItemBookBinding, private val listener: BookListener) :
    RecyclerView.ViewHolder(item.root) {
    fun bind(book: BookEntity) {
        item.tvTitle.text = book.title
        item.tvAuthor.text = book.author
        item.tvGenero.text = book.genre

        item.tvTitle.setOnClickListener { listener.onClick(book.id) }

        when (book.genre) {
            "Terror" -> item.tvGenero.setBackgroundResource(R.drawable.rounded_label_red)
            "Fantasia" -> item.tvGenero.setBackgroundResource(R.drawable.rounded_label_fantasy)
            else -> item.tvGenero.setBackgroundResource(R.drawable.rounded_label_teal)
        }

        updateFavoriteIcon(book.favorite)
    }

    private fun updateFavoriteIcon(favorite: Boolean) {
        val icon = if (favorite) R.drawable.ic_favorite else R.drawable.ic_favorite_empty
        item.ivFavorite.setImageResource(icon)
    }
}