package com.dicoding.sahamiq.core.ui

import android.annotation.*
import android.content.*
import android.net.*
import android.view.*
import androidx.recyclerview.widget.*
import coil.*
import com.dicoding.sahamiq.core.*
import com.dicoding.sahamiq.core.data.source.remote.response.*
import com.dicoding.sahamiq.core.databinding.*
import com.dicoding.sahamiq.core.domain.model.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

    private var listData = ArrayList<ResultsItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<ResultsItem>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()

    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListNewsBinding.bind(itemView)
        private val context: Context = itemView.context

        fun bind(resultsItem: ResultsItem) {
            binding.ivLogoPublisher.load(resultsItem.publisher?.logo) {
                crossfade(true)
            }
            binding.ivImageView.load(resultsItem.image) {
                crossfade(true)
            }
            binding.tvNamePublisher.text = resultsItem.publisher?.name
            binding.tvTitle.text = resultsItem.title
            binding.tvPublishedAt.text = resultsItem.publishedAt
            binding.tvDescription.text = resultsItem.description

            itemView.setOnClickListener {
                val url = resultsItem.url
                if (url!!.isNotEmpty()) {
                    openBrowser(url)
                }
            }
        }

        private fun openBrowser(url: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            context.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_news, parent, false)
    )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}