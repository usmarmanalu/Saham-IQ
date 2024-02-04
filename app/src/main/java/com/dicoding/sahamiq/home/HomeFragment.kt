package com.dicoding.sahamiq.home

import android.content.*
import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import com.dicoding.*
import com.dicoding.sahamiq.*
import com.dicoding.sahamiq.core.data.source.*
import com.dicoding.sahamiq.core.ui.*
import com.dicoding.sahamiq.databinding.*
import com.dicoding.sahamiq.detail.*
import javax.inject.*

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val sahamAdapter = SahamAdapter()
            sahamAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.saham.observe(viewLifecycleOwner) { saham ->
                if (saham != null) {
                    when (saham) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            sahamAdapter.setData(saham.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                saham.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvSaham) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = sahamAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}