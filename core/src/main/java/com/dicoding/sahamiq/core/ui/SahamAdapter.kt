package com.dicoding.sahamiq.core.ui

import android.annotation.*
import android.view.*
import androidx.recyclerview.widget.*
import coil.*
import com.dicoding.sahamiq.core.*
import com.dicoding.sahamiq.core.data.source.*
import com.dicoding.sahamiq.core.databinding.*
import com.dicoding.sahamiq.core.domain.model.*

class SahamAdapter : RecyclerView.Adapter<SahamAdapter.ListViewHolder>() {

    private var listData = ArrayList<Saham>()
    var onItemClick: ((Saham) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Saham>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()

    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSahamBinding.bind(itemView)

        fun bind(data: Saham) {
            with(binding) {
                ivLogo.load(data.company.logo) {
                    crossfade(true)
                }

                tvSymbol.text = data.company.symbol
                tvName.text = data.company.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_saham, parent, false)
    )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

}
