package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LocationAdapter(private var locations: List<ShootingLocation>) : RecyclerView.Adapter<LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(
            R.layout.row_location, parent,
            false
        )
        return LocationViewHolder(row)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val place = locations[position]
        holder.movie.text = place.title
        holder.director.text = place.director
        holder.date.text = place.date.toString()
        holder.address.text = place.address
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    fun refreshData(allLocations: List<ShootingLocation>) {
        this.locations = allLocations
    }
}