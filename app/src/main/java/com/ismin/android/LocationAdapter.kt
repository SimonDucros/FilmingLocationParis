package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter allowing to display a location
 */
class LocationAdapter(private var locations: List<ShootingLocation>, val homeFragment: ListFragment) : RecyclerView.Adapter<LocationViewHolder>() {
    private lateinit var place : ShootingLocation

    /**
     * On view holder creation set the created row on click listener (that starts the detail activity)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_location, parent,false)

        var holder = LocationViewHolder(row)

        row.setOnClickListener {
            homeFragment.startDetailActivityFromFragment(holder.id,holder.fav)
        }
        return holder
    }

    /**
     * Display the view
     */
    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        place = locations[position]
        holder.movie.text = place.title
        holder.director.text = place.director
        holder.date.text = place.date.year.toString()
        holder.address.text = place.address
        holder.id = place.locationId
        holder.fav = place.favourite

        displayShootingTypeIcon(holder,place.shootingType)
        displayFavouriteStatus(holder)
    }

    /**
     * Display full star if the location is a favourite, display an empty star otherwise
     */
    private fun displayFavouriteStatus(holder : LocationViewHolder) {
        if(holder.fav){
            holder.star.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            holder.star.setImageResource(R.drawable.ic_baseline_star_outline_24)
        }
    }

    /**
     * Display an icon representing the shooting type of the movie
     */
    private fun displayShootingTypeIcon(holder : LocationViewHolder, shootingType : String){
        if(shootingType == "Long métrage"){
            holder.image.setImageResource(R.drawable.clap)
        } else if(shootingType == "Série TV"){
            holder.image.setImageResource(R.drawable.tvshow)
        } else if(shootingType == "Série Web"){
            holder.image.setImageResource(R.drawable.computer)
        } else if(shootingType == "Téléfilm"){
            holder.image.setImageResource(R.drawable.tv)
        }
    }

    /**
     * Return the size of the list that is to be displayed
     */
    override fun getItemCount(): Int {
        return locations.size
    }

    /**
     * Refreshes the list of locations
     */
    fun refreshData(allLocations: List<ShootingLocation>) {
        this.locations = allLocations
    }
}