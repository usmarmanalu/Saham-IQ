package com.dicoding.sahamiq.news

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import com.dicoding.sahamiq.core.data.source.remote.network.*
import com.dicoding.sahamiq.core.ui.*
import com.dicoding.sahamiq.databinding.*
import org.koin.androidx.viewmodel.ext.android.*

class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModel()
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val newsAdapter: NewsAdapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        observeNewsList()
    }

    private fun setupRecyclerView() {
        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    private fun observeNewsList() {
        newsViewModel.news.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is ApiResponse.Empty -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ApiResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.setData(resource.data)
                }
                is ApiResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text =
                        resource.errorMessage
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
