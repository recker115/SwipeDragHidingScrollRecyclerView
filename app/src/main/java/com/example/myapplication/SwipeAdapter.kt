package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SwipeAdapter(val stringsList : MutableList<String>) : RecyclerView.Adapter<SwipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwipeViewHolder {
        return SwipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.swipe_item, parent,false))
    }

    override fun getItemCount(): Int {
        return stringsList.size
    }

    override fun onBindViewHolder(holder: SwipeViewHolder, position: Int) {
    }
}