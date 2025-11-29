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
import net.baudovalentim.mybooks.databinding.FragmentHomeBinding
import net.baudovalentim.mybooks.ui.adapter.BookAdapter
import net.baudovalentim.mybooks.ui.listeners.BookListener
import net.baudovalentim.mybooks.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rvBooks.layoutManager = LinearLayoutManager(context)
        binding.rvBooks.adapter = adapter

        setObservers()
        attachListeners()

        val root: View = binding.root
        return root
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getAllBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        homeViewModel.books.observe(viewLifecycleOwner) {
            adapter.updateBooks(it)
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
                homeViewModel.favorite(id)
                homeViewModel.getAllBooks()
            }
        })
    }
}