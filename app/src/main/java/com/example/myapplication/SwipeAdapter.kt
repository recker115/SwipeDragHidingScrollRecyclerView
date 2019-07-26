package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class SwipeAdapter(val stringsList : MutableList<String>) : RecyclerView.Adapter<SwipeViewHolder>() {
    val asyncListDiffer : AsyncListDiffer<String> = AsyncListDiffer(this, object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return false
        }
    })
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        return SwipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.swipe_item, parent,false))
    }

    override fun getItemCount(): Int {
        return stringsList.size
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
    }
}