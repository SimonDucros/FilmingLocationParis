package com.ismin.android

import java.util.*

/**
 * Data structure of a shooting location of the database
 */
data class ShootingLocation(
    val coord_y: Float,
    val shootingType: String,
    val producer: String,
    val date_fin: Date,
    val geoLocation: DoubleArray,
    val title: String,
    val postalCode: String,
    val geo_shape: Objects,
    val locationId: String,
    val director: String,
    val address: String,
    val date_debut: Date,
    val date: Date,
    val coord_x: Float,
    var favourite: Boolean
): java.io.Serializable


/*
data class ShootingLocation(val locationId: String,
                            val date: Date,
                            val shootingType: String,
                            val title: String,
                            val director: String,
                            val producer: String,
                            val address: String,
                            val postalCode: String,
                            val geoLocation: DoubleArray,
                            var favourite: Boolean
                            ): java.io.Serializable

*/