package com.ismin.android

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class LocationViewHolder(rootView: View): ViewHolder(rootView) {
    var movie = rootView.findViewById<TextView>(R.id.r_location_txv_movie)
    var address = rootView.findViewById<TextView>(R.id.r_location_txv_address)
    var date = rootView.findViewById<TextView>(R.id.r_location_txv_releasedate)
    var director = rootView.findViewById<TextView>(R.id.r_movie_txv_director)
    var image = rootView.findViewById<ImageView>(R.id.r_location_image)
}