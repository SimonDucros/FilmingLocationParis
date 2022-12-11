package com.ismin.android

import java.util.*

/**
 * Data structure of a shooting location of the database
 */
data class ShootingLocation(
    val coord_y: Float,
    val type_tournage: String,
    val nom_producteur: String,
    val date_fin: Date,
    val geo_point_2d: DoubleArray,
    val nom_tournage: String,
    val ardt_lieu: String,
    val geo_shape: Objects,
    val id_lieu: String,
    val nom_realisateur: String,
    val adresse_lieu: String,
    val date_debut: Date,
    val annee_tournage: Date,
    val coord_x: Float,
    var favourite: Boolean
): java.io.Serializable