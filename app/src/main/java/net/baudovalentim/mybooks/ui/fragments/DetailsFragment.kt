package net.baudovalentim.mybooks.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.baudovalentim.mybooks.R
import net.baudovalentim.mybooks.viewmodels.DetailsViewModel
import net.baudovalentim.mybooks.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val detailsdViewModel: DetailsViewModel by viewModels()
    private var bookId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        setListeners()
        setObservers()

        bookId = arguments?.getInt("bookId") ?: 0
        detailsdViewModel.getBookById(bookId)


        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        detailsdViewModel.book.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it.title
            binding.tvAuthorValue.text = it.author
            binding.tvGenreValue.text = it.genre
            binding.cbFavorite.isChecked = it.favorite

            when (it.genre) {
                "Terror" -> binding.tvGenreValue.setBackgroundResource(R.drawable.rounded_label_red)
                "Fantasia" -> binding.tvGenreValue.setBackgroundResource(R.drawable.rounded_label_fantasy)
                else -> binding.tvGenreValue.setBackgroundResource(R.drawable.rounded_label_teal)
            }
        }

        detailsdViewModel.bookRemoval.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_removed_successfully),
                    Toast.LENGTH_SHORT
                ).show()

                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }
        binding.btRemove.setOnClickListener { handleRemove() }
        binding.cbFavorite.setOnClickListener { handleFavorite() }
    }

    private fun handleFavorite() {
        detailsdViewModel.toggleFavorite(bookId)
    }

    private fun handleRemove() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(R.string.dialog_message_delete_item)
            .setPositiveButton(
                R.string.dialog_positive_button_yes
            ) { dialog, which -> detailsdViewModel.deleteBook(bookId) }
            .setNegativeButton(
                R.string.dialog_negative_button_no
            ) { dialog, which -> dialog.dismiss() }

        builder.create().show()
    }
}