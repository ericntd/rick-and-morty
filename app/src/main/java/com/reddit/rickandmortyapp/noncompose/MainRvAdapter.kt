package com.reddit.rickandmortyapp.noncompose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reddit.rickandmortyapp.R
import com.reddit.rickandmortyapp.network.RickAndMortyCharacter

class MainRvAdapter: RecyclerView.Adapter<MainRvAdapter.MyViewHolder>() {
    val items = mutableListOf<RickAndMortyCharacter>()

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: RickAndMortyCharacter) {
            itemView.findViewById<TextView>(R.id.character_name).text = item.name
            val imageView = itemView.findViewById<ImageView>(R.id.image)
            Glide.with(itemView.context).load(item.image).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(layoutInflater.inflate(R.layout.character_item_view, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun update(it: List<RickAndMortyCharacter>) {
        items.clear()
        items.addAll(it)
        notifyDataSetChanged()
    }
}