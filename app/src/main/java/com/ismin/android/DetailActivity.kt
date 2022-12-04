package com.ismin.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

val FAVOURITE_MODIFICATION = "FAVOURITE_MODIFICATION";

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        /* TODO
get location from main activity


 */
        val location = ShootingLocation() // replace by charging the actual location !!!!!

        // display location's details
        findViewById<TextView>(R.id.a_detail_id).text = location.locationId
        findViewById<TextView>(R.id.a_detail_director).text = location.director
        findViewById<TextView>(R.id.a_detail_movie).text = location.title
        findViewById<TextView>(R.id.a_detail_address).text = location.address
        findViewById<TextView>(R.id.a_detail_postalcode).text = location.postalCode
        findViewById<TextView>(R.id.a_detail_producer).text = location.producer
        findViewById<TextView>(R.id.a_detail_type).text = location.shootingType
        findViewById<TextView>(R.id.a_detail_date).text = location.date.toString()

        findViewById<Button>(R.id.a_detail_btn_backarrow).setOnClickListener(this::returnToMain)
    }

    private fun returnToMain(_v: View) {
        // we need the location so that the favourite variable can be updated

        val intent = Intent()
        //      intent.putExtra(FAVOURITE_MODIFICATION, location)
        setResult(RESULT_OK, intent)
        finish()
    }
}