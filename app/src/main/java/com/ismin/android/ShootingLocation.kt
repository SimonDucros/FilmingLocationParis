package com.ismin.android

import java.util.*

data class ShootingLocation(val locationId: String,
                            val date: Date,
                            val shootingType: String,
                            val title: String,
                            val director: String,
                            val producer: String,
                            val address: String,
                            val postalCode: String,
                            val geoLocation: DoubleArray,
                      //      val favourite: Boolean
                            ): java.io.Serializable

