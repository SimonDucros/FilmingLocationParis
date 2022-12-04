package com.ismin.android

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://shootingLocations-gme.cleverapps.io"


class MainActivity : AppCompatActivity() {

    private val locations = ShootingLocation()
    private val btnRefresh: FloatingActionButton by lazy { findViewById(R.id.a_main_btn_refresh) }

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()
    val shootingLocationService = retrofit.create(ShootingLocationService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shootingLocationService.getAllBooks()
            .enqueue(object : Callback<List<ShootingLocation>> {
                override fun onResponse(
                    call: Call<List<ShootingLocation>>,
                    response: Response<List<ShootingLocation>>
                ) {
                    response.body()?.forEach { locations.addShootingLocation(it) }
                    displayListFragment()
                }
                override fun onFailure(call: Call<List<ShootingLocation>>, t: Throwable) {
                    Toast.makeText(applicationContext,"Cannot display books", Toast.LENGTH_SHORT).show()
                }
            })

        btnRefresh.setOnClickListener {
            displayListFragment()
        }
    }

    private fun displayListFragment() {
        val bookListFragment = ListFragment.newInstance(locations.getAllLocations())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, bookListFragment)
            .commit()
    }

    private fun displayAppInfoFragment() {
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
            else -> super.onOptionsItemSelected(item)
        }
    }

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

}