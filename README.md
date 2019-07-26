# SwipeDragHidingScrollRecyclerView

Add to **root** `build.gradle` :-

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 then add the following to app-level `build.gradle`
 
    implementation 'com.github.recker115:SwipeDragHidingScrollRecyclerView:v0.1.0'
    
 Usage instruction :-

    <com.example.swiperecyclerview.SwipeRecyclerView
            android:id="@+id/rvSwipe"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:listitem="@layout/swipe_item"
            app:icon_margin="16dp"
            app:swipe_icon="@drawable/ic_delete_sweep_black_24dp"
            app:swipe_background="@color/colorAccent"
            tools:itemCount="10"/>
            
 to `layouts.xml` and pass the attributes :-
 
     app:swipe_icon="@drawable/ic_delete_sweep_black_24dp"
     app:swipe_background="@color/colorAccent"
 
 i.e, the `swipe background color` and the `swipe background icon` that you wanna show when the user swipes.
 
 Finally the **last step** is to add the callback , *after the item is successfully swiped right or left**
 
     rvSwipe.setSwipeCallback(object : SwipeRecyclerView.SwipeCallBack {
            override fun onSwiped(position: Int, direction: Int) {
                list.removeAt(position) // remove from the main list
                rvSwipe.adapter?.notifyItemRemoved(position) // then notify the recyclerView adapter.
            }
        })
        
 P.S :- **SwipeRecyclerView** is an extension of **RecylerView** so all functionalities of recyclerView are available.      
