package com.ismin.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/*import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 */

const val SERVER_BASE_URL = "https://shootingLocations-gme.cleverapps.io"


class MainActivity : AppCompatActivity() {

    private val locations = ListShootingLocations()
/*
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()
    val shootingLocationService = retrofit.create(ShootingLocationService::class.java)
 */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

 /*       shootingLocationService.getAllBooks()
            .enqueue(object : Callback<List<ShootingLocation>> {
                override fun onResponse(
                    call: Call<List<ShootingLocation>>,
                    response: Response<List<ShootingLocation>>
                ) {
                    response.body()?.forEach { locations.addShootingLocation(it) }
                    displayListFragment()
                }
                override fun onFailure(call: Call<List<ShootingLocation>>, t: Throwable) {
                    Toast.makeText(applicationContext,"Cannot display locations", Toast.LENGTH_SHORT).show()
                }
            })
  */
        // for tests
        val shoot = ShootingLocation("2019-1719", Date(2019), "Long métrage", "30 Jours Max","Tarek BOUDALI","AXEL FILMS PRODUCTION","rue rené clair, 75018 paris","75018",doubleArrayOf(48.87219487147879,2.303550627818585))
        locations.addShootingLocation(shoot)
        displayListFragment()
    }

    private fun displayListFragment() {
        val bookListFragment = ListFragment.newInstance(locations.getAllShootingLocations())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, bookListFragment)
            .commit()
    }

    private fun displayAppInfoFragment() {
        val id = "2019-1719"
        val appInfoFragment = AppInfoFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, appInfoFragment)
            .commit()
    }

    private fun displayMapFragment() {
/*        val mapFragment = MapFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, mapFragment)
            .commit()
        btnCreateBook.hide()
 */
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_list -> {
                displayListFragment()
                true
            }
            R.id.menu_map -> {
                displayMapFragment()
                true
            }
            R.id.menu_info -> {
                displayAppInfoFragment()
                true
            }
            R.id.menu_refresh -> {
                displayListFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
    override fun onBookCreated(shootLocation: ShootingLocation) {
        shootingLocationService.createBook(shootLocation)
            .enqueue(object : Callback<ShootingLocation> {
                override fun onResponse(call: Call<ShootingLocation>,response: Response<ShootingLocation>) {
                    response.body()?.let{locations.addBook(it)}
                    displayListFragment()
                }
                override fun onFailure(call: Call<ShootingLocation>, t: Throwable) {
                    Toast.makeText(applicationContext,"Cannot be added to favourites", Toast.LENGTH_SHORT).show()
                }
            })
    }

     */

}