package com.dicoding.sahamiq.core.ui

import android.view.*
import androidx.recyclerview.widget.*
import coil.*
import com.dicoding.sahamiq.core.*
import com.dicoding.sahamiq.core.databinding.*
import com.dicoding.sahamiq.core.domain.model.*

class SahamAdapter : RecyclerView.Adapter<SahamAdapter.ListViewHolder>() {

    private var listData = ArrayList<Saham>()
    var onItemClick: ((Saham) -> Unit)? = null

    fun setData(newListData: List<Saham>?) {
        if (newListData == null) return
        val diffCallback = SahamDiffCallback(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_saham, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    class SahamDiffCallback(private val oldList: List<Saham>, private val sahamList: List<Saham>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = sahamList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].company.symbol == sahamList[newItemPosition].company.symbol

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == sahamList[newItemPosition]
    }
}
