package net.baudovalentim.mybooks.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import net.baudovalentim.mybooks.R
import net.baudovalentim.mybooks.databinding.ItemBookBinding
import net.baudovalentim.mybooks.entity.BookEntity

class BookViewHolder(private val item: ItemBookBinding): RecyclerView.ViewHolder(item.root) {
    fun bind(book: BookEntity) {
        item.tvTitle.text = book.title
        item.tvAuthor.text = book.author
        item.tvGenero.text = book.genre

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