package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swiperecyclerview.SwipeRecyclerView
import kotlinx.android.synthetic.main.activity_fullscreen.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        val list = mutableListOf("a","b","","","","","","","","","")

        rvSwipe.adapter = SwipeAdapter(list)
        rvSwipe.layoutManager = LinearLayoutManager(this)
        rvSwipe.setSwipeCallback(object : SwipeRecyclerView.SwipeCallBack {
            override fun onSwiped(position: Int, direction: Int) {
                list.removeAt(position)
                rvSwipe.adapter?.notifyDataSetChanged()
            }

            override fun onSwapped(positionfrom: Int, positionTo: Int) {
            }
        })
    }
}
