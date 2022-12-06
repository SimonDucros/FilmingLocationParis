package com.ismin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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

    private var locations = ListShootingLocations()
    private lateinit var locationAdapter: LocationAdapter

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
        val shoot2 = ShootingLocation("2019-1719", Date(2019,1,1), "Long métrage", "30 Jours Max","Tarek BOUDALI","AXEL FILMS PRODUCTION","rue rené clair, 75018 paris","75018",doubleArrayOf(48.87219487147879,2.303550627818585),false)
        val shoot = ShootingLocation("2019-1718", Date(2012,1,1), "Téléfilm", "0 Jours Max","Dieu","Moi","rue imaginaire","75018",doubleArrayOf(48.87219487147879,2.303550627818585),false)
        val shoot3 = ShootingLocation("2019-1720", Date(2022,1,1), "Série Web", "Hello","Pourriture","Moi","rue imaginaire","75018",doubleArrayOf(48.87219487147879,2.303550627818585),false)
        locations.addShootingLocation(shoot)
        locations.addShootingLocation(shoot2)
        locations.addShootingLocation(shoot3)
        displayListFragment()
    }

    fun startDetailActivity(id: String, fav: Boolean) {
        val intent = Intent(this, DetailActivity::class.java)
        locations = locations.updateFavourites(locations,id,fav)
        var shootingLocation = locations.getShootingLocationById(id)
        intent.putExtra("location", shootingLocation)
        startForResult.launch(intent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val changedLocation = result.data?.getSerializableExtra(FAVOURITE_MODIFICATION) as ShootingLocation
            locations = locations.updateFavourites(locations,changedLocation.locationId, changedLocation.favourite)
            locationAdapter.refreshData(locations.getAllShootingLocations())
            locationAdapter.notifyDataSetChanged()
            displayListFragment()
        }
    }

    private fun displayListFragment() {
        val listFragment = ListFragment.newInstance(locations.getAllShootingLocations())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, listFragment)
            .commit()
        locationAdapter = LocationAdapter(locations.getAllShootingLocations(),listFragment)
    }

    private fun displayAppInfoFragment() {
        val appInfoFragment = AppInfoFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, appInfoFragment)
            .commit()
    }

    private fun displayMapFragment() {
        val mapFragment = MapsFragment.newInstance(locations)
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, mapFragment)
            .commit()
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