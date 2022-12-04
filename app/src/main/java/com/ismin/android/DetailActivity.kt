package com.ismin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import java.util.*

val FAVOURITE_MODIFICATION = "FAVOURITE_MODIFICATION";

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        /* TODO
get location from main activity
 */
        val location = ShootingLocation("2019-1719", Date(2019), "Long métrage", "30 Jours Max",
        "Tarek BOUDALI","AXEL FILMS PRODUCTION","rue rené clair, 75018 paris","75018",doubleArrayOf(48.87219487147879,2.303550627818585))
        // replace by charging the actual location !!!!!

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


    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val location = result.data?.getSerializableExtra(FAVOURITE_MODIFICATION) as ShootingLocation
            // Do amazing things with your returned data: data.getExtra(...)
        }
    }


    fun startActivity2() {
        val intent = Intent(this, MainActivity::class.java)
        startForResult.launch(intent)
    }

    private fun returnToMain(_v: View) {
        // we need the location so that the favourite variable can be updated

        val intent = Intent()
 //       intent.putExtra(FAVOURITE_MODIFICATION, location)
        setResult(RESULT_OK, intent)
        finish()
    }
}
