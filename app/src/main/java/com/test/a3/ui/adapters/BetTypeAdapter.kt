package com.test.a3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.a3.data.network.response.TypesBetResponse
import com.test.a3.databinding.BetTypeItemBinding

class BetTypeAdapter(
    private val layoutInflater: LayoutInflater,
    val listener: (position: Int) -> Unit
) : RecyclerView.Adapter<BetTypeAdapter.BetTypeAdapterViewHolder>() {
    private var items = mutableListOf<TypesBetResponse>()

    fun updateList(newList: List<TypesBetResponse>) {
        items.clear()
        items.addAll(newList)

        notifyDataSetChanged()
    }


    class BetTypeAdapterViewHolder(
        private val binding: BetTypeItemBinding,
        private val listener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(betType: TypesBetResponse, position: Int) {
            binding.tvBetTitle.text = betType.title
            binding.tvBetTitle.setOnClickListener {
                listener.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BetTypeAdapterViewHolder {
        return BetTypeAdapterViewHolder(
            BetTypeItemBinding.inflate(
                layoutInflater,
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: BetTypeAdapterViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size


}