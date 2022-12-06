package com.ismin.android

import java.util.*

/**
 * Data structure of a shooting location of the database
 */
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

/*
data class ShootingLocation(val id_lieu: String,
                            val annee_tournage: Date,
                            val type_tournage: String,
                            val nom_tournage: String,
                            val nom_realisateur: String,
                            val nom_producteur: String,
                            val adresse_lieu: String,
                            val ardt_lieu: String,
                            val date_debut: Date,
                            val date_fin: Date,
                            val coord_x: Float,
                            val coord_y: Float,
                            val geo_shape: Objects,
                            val geo_point_2d: DoubleArray,
                            var favourite: Boolean
                            ): java.io.Serializable
 */