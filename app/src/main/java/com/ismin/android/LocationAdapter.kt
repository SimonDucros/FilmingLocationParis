package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class LocationAdapter(private var locations: List<ShootingLocation>, val homeFragment: ListFragment) : RecyclerView.Adapter<LocationViewHolder>() {
    private lateinit var place : ShootingLocation

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_location, parent,false)

        row.setOnClickListener {
            homeFragment.startDetailActivityFromFragment(place)
        }
        return LocationViewHolder(row)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        place = locations[position]
        holder.movie.text = place.title
        holder.director.text = place.director
        holder.date.text = place.date.toString()
        holder.address.text = place.address

        if(place.shootingType.equals("Long métrage")){
            holder.image.setImageResource(R.drawable.clap)
        } else if(place.shootingType.equals("Série TV")){
            holder.image.setImageResource(R.drawable.tvshow)
        } else if(place.shootingType.equals("Série Web")){
            holder.image.setImageResource(R.drawable.computer)
        } else if(place.shootingType.equals("Téléfilm")){
            holder.image.setImageResource(R.drawable.tv)
        }
    }


    override fun getItemCount(): Int {
        return locations.size
    }

    fun refreshData(allLocations: List<ShootingLocation>) {
        this.locations = allLocations;
    }
}