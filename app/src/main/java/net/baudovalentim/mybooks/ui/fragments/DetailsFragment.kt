package net.baudovalentim.mybooks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }
    }
}