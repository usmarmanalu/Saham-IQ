package com.dicoding.sahamiq.detail

import android.graphics.*
import android.os.*
import androidx.activity.*
import androidx.appcompat.app.*
import androidx.core.content.*
import coil.*
import com.dicoding.*
import com.dicoding.sahamiq.R
import com.dicoding.sahamiq.core.data.source.remote.response.*
import com.dicoding.sahamiq.core.domain.model.*
import com.dicoding.sahamiq.core.ui.*
import com.dicoding.sahamiq.core.utils.*
import com.dicoding.sahamiq.databinding.*
import org.koin.androidx.viewmodel.ext.android.*

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {


    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var binding: ActivityDetailBinding
    private lateinit var lineChartHelper: ChartHelper

    override fun onCreate(savedInstanceState: Bundle?) {
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
            binding.content.tvDetailSaham.text = getString(
                com.dicoding.sahamiq.core.R.string.text_subtitle,
                "${detailSaham.company.name}"
            )

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

            val lineChart = binding.content.chartSaham
            lineChartHelper = ChartHelper(lineChart)
            lineChartHelper.setupBarChart()

            val lineData = lineChartHelper.createBarData(listOf(detailSaham))
            lineChart.data = lineData
            lineChart.invalidate()

            val changeAndPercent = getString(R.string.percent_ratio)
            val close = getString(R.string.dollar_currency)

            binding.content.tvChange.setTextColor(Color.RED)
            binding.content.tvChange.text =
                getString(R.string.change_text, "${detailSaham.change}$changeAndPercent")

            binding.content.tvPercent.setTextColor(Color.GREEN)
            binding.content.tvPercent.text =
                getString(R.string.percent_text, "${detailSaham.percent}$changeAndPercent")

            binding.content.tvClose.setTextColor(Color.BLUE)
            binding.content.tvClose.text =
                getString(R.string.close_text, "$close${detailSaham.close}")

        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bookmark
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.bookmark_border
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