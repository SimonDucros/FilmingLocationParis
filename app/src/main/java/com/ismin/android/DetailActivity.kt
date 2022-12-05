package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

const val FAVOURITE_MODIFICATION = "FAVOURITE_MODIFICATION"

class DetailActivity : AppCompatActivity() {
    private lateinit var location : ShootingLocation

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

            if(location.shootingType.equals("Long métrage")){
                findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.clap)
            } else if(location.shootingType.equals("Série TV")){
                findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.tvshow)
            } else if(location.shootingType.equals("Série Web")){
                findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.computer)
            } else if(location.shootingType.equals("Téléfilm")){
                findViewById<ImageView>(R.id.a_detail_image).setImageResource(R.drawable.tv)
            }
            displayFavouriteStatus(location.favourite)

            findViewById<FloatingActionButton>(R.id.a_detail_btn_backarrow).setOnClickListener(this::returnToMain)
            findViewById<ImageView>(R.id.a_detail_favourite_status).setOnClickListener { location = updateFavouriteStatus(location) }
        }
    }

    private fun updateFavouriteStatus(location: ShootingLocation): ShootingLocation {
        location.favourite = !location.favourite
        displayFavouriteStatus(location.favourite)
        return location
    }

    private fun displayFavouriteStatus(fav: Boolean){
        if(fav){
            findViewById<ImageView>(R.id.a_detail_favourite_status).setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            findViewById<ImageView>(R.id.a_detail_favourite_status).setImageResource(R.drawable.ic_baseline_star_outline_24)
        }
    }

    private fun returnToMain(_v: View) {
        // we need the location so that the favourite variable can be updated

        val intent = Intent()
        intent.putExtra(FAVOURITE_MODIFICATION, location)
        setResult(RESULT_OK, intent)
        finish()
    }
}
