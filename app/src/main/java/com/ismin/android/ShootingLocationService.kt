package com.ismin.android

import retrofit2.Call
import retrofit2.http.*

interface ShootingLocationService {
    @GET("shootingLocations")
    fun getAllShootingLocations(): Call<List<ShootingLocation>>

    @GET("shootingLocations")
    fun getShootingLocationByID(@Body() id:String): Call<ShootingLocation>

    @POST("shootingLocations")
    fun createShootingLocation(@Body() shootingLocationToCreate: ShootingLocation): Call<ShootingLocation>

    /*
    @DELETE("shootingLocations")
    fun deleteShootingLocation(@Body() locationId: String): void
     */

    @PUT("")
    fun updateFavourite(@Body() locationId: String, favourite: Boolean): Call<ShootingLocation>
}