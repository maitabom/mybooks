package net.baudovalentim.mybooks.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.baudovalentim.mybooks.R
import net.baudovalentim.mybooks.databinding.FragmentFavoriteBinding
import net.baudovalentim.mybooks.ui.adapter.BookAdapter
import net.baudovalentim.mybooks.ui.listeners.BookListener
import net.baudovalentim.mybooks.viewmodels.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvBooksFavorite.layoutManager = LinearLayoutManager(context)
        binding.rvBooksFavorite.adapter = adapter

        attachListeners()
        setObservers()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavoriteBooks()
    }

    private fun setObservers() {
        favoriteViewModel.books.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.tvNoBooks.visibility = View.VISIBLE
                binding.ivNoBooks.visibility = View.VISIBLE
                binding.rvBooksFavorite.visibility = View.GONE
            } else {
                binding.tvNoBooks.visibility = View.GONE
                binding.ivNoBooks.visibility = View.GONE
                binding.rvBooksFavorite.visibility = View.VISIBLE
                adapter.updateBooks(it)
            }
        }
    }

    private fun attachListeners() {
        adapter.attachListener(object : BookListener {
            override fun onClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt("bookId", id)

                findNavController().navigate(R.id.navigation_details, bundle)
            }

            override fun onFavoriteClick(id: Int) {
                favoriteViewModel.favorite(id)
                favoriteViewModel.getFavoriteBooks()
            }
        })
    }
}