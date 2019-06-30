package com.example.swiperecyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 *  Create by Santanu ^_^ on 29/06/2019
 */
class SwipeRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {
    var colorDrawable: ColorDrawable? = null
    var iconDrawable: Drawable? = null
    var margin : Float = 0f
    var swipeCallBack : SwipeCallBack ?= null

    /**
     *  Item touch helper callback object creation
     *  Anonymously
     */
    val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                swipeCallBack?.onSwiped(viewHolder.adapterPosition, direction)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                val itemView = viewHolder.itemView
                val bounds = dX.toInt().getBounds(itemView)
                colorDrawable?.setBounds(bounds[0], bounds[1], bounds[2], bounds[3])
                colorDrawable?.draw(c)
                iconDrawable?.setBounds(bounds[4], bounds[5], bounds[6], bounds[7])
                iconDrawable?.draw(c)
            }
        }
    )

    /**
     *  For getting bounds based on the value of drag (dX )
     *  @param dX
     *  @param itemView
     *  @return IntArray ( bounds for the background view )
     */
    fun Int.getBounds(itemView: View): IntArray {
        val bounds = IntArray(8)
        var leftBackground = 0
        var topBackground = 0
        var rightBackground = 0
        var bottomBackground = 0
        var leftIcon = 0
        var topIcon = 0
        var rightIcon = 0
        var bottomIcon = 0
        when {
            this > 0 -> {
                leftBackground = itemView.left
                topBackground = itemView.top
                rightBackground = itemView.left + this + 20 // 20 is offset context
                bottomBackground = itemView.bottom

                val iconTopToBottom = itemView.bottom - iconDrawable?.intrinsicHeight!!
                leftIcon = itemView.left + margin.toInt()
                topIcon = iconTopToBottom - (iconTopToBottom - itemView.top) / 2
                rightIcon = leftIcon+ iconDrawable?.intrinsicWidth!!
                bottomIcon = itemView.bottom - (iconTopToBottom - itemView.top) /2

            }
            this < 0 -> {
                leftBackground = itemView.right + this - margin.toInt()
                topBackground = itemView.top
                rightBackground = itemView.right
                bottomBackground = itemView.bottom

                val iconTopToBottom = itemView.bottom - iconDrawable?.intrinsicHeight!!
                leftIcon = itemView.right - iconDrawable?.intrinsicWidth!! - margin.toInt()
                topIcon = iconTopToBottom - (iconTopToBottom - itemView.top) / 2
                rightIcon = itemView.right - margin.toInt()
                bottomIcon = itemView.bottom - (iconTopToBottom - itemView.top) /2
            }
        }
        bounds[0] = leftBackground
        bounds[1] = topBackground
        bounds[2] = rightBackground
        bounds[3] = bottomBackground
        bounds[4] = leftIcon
        bounds[5] = topIcon
        bounds[6] = rightIcon
        bounds[7] = bottomIcon

        return bounds
    }


    /**
     *  Looping through attrs and getting the user input
     *  res id for :- color && drawable icon
     */
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwipeRecyclerView)
        for (index in 0 until typedArray.indexCount) {
            val attr = typedArray.getIndex(index)
            when (attr) {
                R.styleable.SwipeRecyclerView_swipe_background -> colorDrawable = ColorDrawable(typedArray.getColor(attr, 0))
                R.styleable.SwipeRecyclerView_swipe_icon -> iconDrawable = typedArray.getDrawable(attr)
                R.styleable.SwipeRecyclerView_icon_margin -> margin = typedArray.getDimension(attr, 0f)
            }
        }
        typedArray.recycle()
        itemTouchHelper.attachToRecyclerView(this)
    }

    /**
     *  To notify the activity which position of recyclerview is swiped
     *  and the direction
     */
    interface SwipeCallBack {
        fun onSwiped(position : Int, direction : Int)
    }

    /**
     *  Set the swipe callback from activity
     */
    fun setSwipeCallback(swipeCallBack: SwipeCallBack) {
        this.swipeCallBack = swipeCallBack
    }
}