package com.dicoding.sahamiq.detail

import android.os.*
import androidx.activity.*
import androidx.appcompat.app.*
import androidx.core.content.*
import coil.*
import com.dicoding.*
import com.dicoding.sahamiq.R
import com.dicoding.sahamiq.core.domain.model.*
import com.dicoding.sahamiq.core.ui.*
import com.dicoding.sahamiq.databinding.*
import javax.inject.*

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val detailViewModel: DetailViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailSaham = intent.getParcelableExtra<Saham>(EXTRA_DATA)
        showDetailSaham(detailSaham)
    }

    private fun showDetailSaham(detailSaham: Saham?) {
        detailSaham?.let {
            supportActionBar?.title = detailSaham.company.symbol
            binding.content.tvDetailSaham.text = detailSaham.company.name

            binding.ivDetailLogo.load(detailSaham.company.logo) {
                crossfade(true)

                var statusFavorite = detailSaham.isFavorite
                setStatusFavorite(statusFavorite!!)
                binding.fab.setOnClickListener {
                    statusFavorite = !statusFavorite!!
                    detailViewModel.setFavoriteSaham(detailSaham, statusFavorite!!)
                    setStatusFavorite(statusFavorite!!)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_border_24
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}