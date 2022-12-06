package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Extra specifying that a ShootingLocation can be sent back to the main activity
 */
const val FAVOURITE_MODIFICATION = "FAVOURITE_MODIFICATION"

/**
 * Provides complete information on a location
 */
class DetailActivity : AppCompatActivity() {
    private lateinit var location : ShootingLocation

    /**
     * On creation display all information of a location.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras
        if (extras != null) {
            location = extras.getSerializable("location") as ShootingLocation

            // display location's details
            findViewById<TextView>(R.id.a_detail_id).text = location.locationId
            findViewById<TextView>(R.id.a_detail_director).text = location.director
            findViewById<TextView>(R.id.a_detail_movie).text = location.title
            findViewById<TextView>(R.id.a_detail_address).text = location.address
            findViewById<TextView>(R.id.a_detail_postalcode).text = location.postalCode
            findViewById<TextView>(R.id.a_detail_producer).text = location.producer
            findViewById<TextView>(R.id.a_detail_type).text = location.shootingType
            findViewById<TextView>(R.id.a_detail_date).text = location.date.year.toString()

            displayShootingTypeIcon(location.shootingType)
            displayFavouriteStatus(location.favourite)

            findViewById<FloatingActionButton>(R.id.a_detail_btn_backarrow).setOnClickListener(this::returnToMain)
            findViewById<ImageView>(R.id.a_detail_favourite_status).setOnClickListener { location = updateFavouriteStatus(location) }
        }
    }

    /**
     * Display the icon of a location depending on the shooting type of the movie
     */
    private fun displayShootingTypeIcon(shootingType:String){
        if(shootingType == "Long métrage"){
            findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.clap)
        } else if(shootingType == "Série TV"){
            findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.tvshow)
        } else if(shootingType == "Série Web"){
            findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.computer)
        } else if(shootingType == "Téléfilm"){
            findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.tv)
        }
    }

    /**
     * Update the variable favourite of a location and display the new status in the app
     */
    private fun updateFavouriteStatus(location: ShootingLocation): ShootingLocation {
        location.favourite = !location.favourite
        displayFavouriteStatus(location.favourite)
        return location
    }

    /**
     * Update the display of the favourite status. A full star is displayed if the location is labeled as favourite. An empty star is showed otherwise.
     */
    private fun displayFavouriteStatus(fav: Boolean){
        if(fav){
            findViewById<ImageView>(R.id.a_detail_favourite_status).setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            findViewById<ImageView>(R.id.a_detail_favourite_status).setImageResource(R.drawable.ic_baseline_star_outline_24)
        }
    }

    /**
     * Finish the activity and return to the main activity
     * Send the location that has been displayed back to the main to notify it of the changes
     */
    private fun returnToMain(_v: View) {
        val intent = Intent()
        intent.putExtra(FAVOURITE_MODIFICATION, location)
        setResult(RESULT_OK, intent)
        finish()
    }
}
