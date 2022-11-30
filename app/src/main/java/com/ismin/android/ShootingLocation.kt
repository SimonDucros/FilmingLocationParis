package com.ismin.android

import java.util.*

data class ShootingLocation(val locationId: String,
                            val shootingDate: Date,
                            val shootingType: String,
                            val title: String,
                            val director: String,
                            val producer: String,
                            val address: String,
                            val postalCode: String,
                            val startDate: Date,
                            val endDate: Date,
                            val geoLocation: LongArray,
                            val favourite: Boolean): java.io.Serializable

