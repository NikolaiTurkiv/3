package com.test.a3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.a3.data.network.response.DictionaryResponse
import com.test.a3.databinding.DictionaryItemBinding

class DictionaryAdapter(
    private val layoutInflater: LayoutInflater,
    val listener: (position: Int) -> Unit
) : RecyclerView.Adapter<DictionaryAdapter.DictionaryAdapterViewHolder>() {

    private var items = mutableListOf<DictionaryResponse>()

    fun updateList(newList: List<DictionaryResponse>) {
        items.clear()
        items.addAll(newList)

        notifyDataSetChanged()
    }


    class DictionaryAdapterViewHolder(
        private val binding: DictionaryItemBinding,
        private val listener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dictionary: DictionaryResponse, position: Int) {
            binding.tvDictionaryTitle.text = dictionary.title
            binding.tvDictionaryTitle.setOnClickListener {
                listener.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryAdapterViewHolder {
        return DictionaryAdapterViewHolder(
            DictionaryItemBinding.inflate(
                layoutInflater,
                parent,
                false
            ),listener
        )
    }

    override fun onBindViewHolder(holder: DictionaryAdapterViewHolder, position: Int) {
        holder.bind(items[position],position)
    }

    override fun getItemCount(): Int = items.size
}