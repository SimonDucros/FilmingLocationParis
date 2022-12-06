package com.ismin.android

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class LocationAdapter(private var locations: List<ShootingLocation>, val homeFragment: ListFragment) : RecyclerView.Adapter<LocationViewHolder>() {
    private lateinit var place : ShootingLocation

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_location, parent,false)

        var holder = LocationViewHolder(row)

        row.setOnClickListener {
            homeFragment.startDetailActivityFromFragment(holder.id,holder.fav)
        }
        return holder
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        place = locations[position]
        holder.movie.text = place.title
        holder.director.text = place.director
        holder.date.text = place.date.year.toString()
        holder.address.text = place.address
        holder.id = place.locationId
        holder.fav = place.favourite

        if(place.shootingType == "Long métrage"){
            holder.image.setImageResource(R.drawable.clap)
        } else if(place.shootingType == "Série TV"){
            holder.image.setImageResource(R.drawable.tvshow)
        } else if(place.shootingType == "Série Web"){
            holder.image.setImageResource(R.drawable.computer)
        } else if(place.shootingType == "Téléfilm"){
            holder.image.setImageResource(R.drawable.tv)
        }
        if(holder.fav){
            holder.star.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            holder.star.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }
        Log.i("THIS IS THE PLACE ID",place.locationId)
    }


    override fun getItemCount(): Int {
        return locations.size
    }

    fun refreshData(allLocations: List<ShootingLocation>) {
        this.locations = allLocations;
    }
}