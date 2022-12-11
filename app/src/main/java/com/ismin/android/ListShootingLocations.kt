package com.ismin.android

import java.lang.RuntimeException

class ListShootingLocations{

    private val locations: HashMap<String, ShootingLocation> = HashMap();

    fun addShootingLocation(shootLocation: ShootingLocation) {
        locations[shootLocation.id_lieu] = shootLocation;
    }

    fun getShootingLocationById(id: String): ShootingLocation {
        return locations[id] ?: throw RuntimeException("No book with title: $id");
    }

    fun getAllShootingLocations(): ArrayList<ShootingLocation> {
        return ArrayList(locations.values.sortedBy { it.nom_tournage });
    }

    fun getTotalNumberOfShootingLocations(): Int {
        return locations.size;
    }

    fun updateFavourites(locations : ListShootingLocations,id: String, fav: Boolean): ListShootingLocations {
        locations.getShootingLocationById(id).favourite = fav
        return locations
    }
}
