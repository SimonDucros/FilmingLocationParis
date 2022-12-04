package com.ismin.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
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
            findViewById<TextView>(R.id.a_detail_date).text = location.date.toString()

            findViewById<FloatingActionButton>(R.id.a_detail_btn_backarrow).setOnClickListener(this::returnToMain)
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
