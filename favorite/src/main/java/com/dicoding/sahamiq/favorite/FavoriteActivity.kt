package com.dicoding.sahamiq.favorite

import android.content.*
import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.recyclerview.widget.*
import com.dicoding.sahamiq.core.ui.*
import com.dicoding.sahamiq.detail.*
import com.dicoding.sahamiq.favorite.databinding.*
import org.koin.androidx.viewmodel.ext.android.*
import org.koin.core.context.*

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.favorite)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(favoriteModule)

        val sahamAdapter = SahamAdapter()

        sahamAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        binding.progressBar.visibility = View.VISIBLE

        favoriteViewModel.favoriteSaham.observe(this) { dataSaham ->
            sahamAdapter.setData(dataSaham)
            binding.progressBar.visibility = View.GONE
            binding.viewEmpty.root.visibility =
                if (dataSaham.isNotEmpty()) View.GONE else View.VISIBLE

        }

        with(binding.rvSaham) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = sahamAdapter
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
