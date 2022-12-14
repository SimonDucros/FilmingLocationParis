package com.ismin.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val SERVER_BASE_URL = "http://app-a420d8b0-91fa-4e22-aa10-d7b502ac5499.cleverapps.io"

/**
 * Root navigation activity
 */
class MainActivity : AppCompatActivity() {

    private var locations = ListShootingLocations()
    private lateinit var locationAdapter: LocationAdapter


    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL)
        .build()
    val shootingLocationService = retrofit.create(ShootingLocationService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        TODO uncomment one of the two following lines in order to test the project
        Remote version --> remoteDataFetching()
        Local version --> localTests()
         */
     //   remoteDataFetching()
        localTests()
    }

    /**
     * Fetch remote data base
     */
    private fun remoteDataFetching(){
        /*
        TODO get locations from remote to work with the app
         */
        shootingLocationService.getAllShootingLocations()
            .enqueue(object : Callback<List<ShootingLocation>> {
                override fun onResponse(
                    call: Call<List<ShootingLocation>>,
                    response: Response<List<ShootingLocation>>
                ) {
                    response.body()?.forEach { locations.addShootingLocation(it) }
                    displayListFragment()
                }
                override fun onFailure(call: Call<List<ShootingLocation>>, t: Throwable) {
                    t.cause
                    t.message
                    Toast.makeText(applicationContext,"Cannot display locations", Toast.LENGTH_SHORT).show()
                }
            })
    }

    /**
     * Update remote data base
     */
    fun updateRemoteData(locationId: String, favourite: Boolean){
        /*
        TODO update remote locations
         */
        /*
        shootingLocationService.updateFavourite(locationId,favourite)
            .enqueue(object : Callback<ShootingLocation> {
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
    }

    /**
     * Tests using the local version of the project
     */
    fun localTests(){
        val shoot2 = ShootingLocation("2019-1719", Date(2019,1,1), "Long m??trage", "30 Jours Max","Tarek BOUDALI","AXEL FILMS PRODUCTION","rue ren?? clair, 75018 paris","75018",doubleArrayOf(48.87219487147879,2.303550627818585),false)
        val shoot = ShootingLocation("2019-1712", Date(2019,1,1), "Long m??trage", "CIGARE AU MIEL","Madame KAMIR A??NOUZ","ELIPH PRODUCTIONS","7 rue de berri, 75008 paris","75008",doubleArrayOf(48.87219487147879,2.303550627818585),false)
        val shoot3 = ShootingLocation("2016-644", Date(2016,1,1), "Long m??trage", "CARBONE","OLIVIER MARCHAL","LES FILMS MANUEL MUNZ","PLACE VENDOME","75001",doubleArrayOf(48.86811300034083,2.329704999637536),false)
        val shoot8 = ShootingLocation("2016-620", Date(2016,1,1), "Long m??trage", "DIANE A LES EPAULES","FABIEN GORGEART","PETIT FILM","3  IMPASSE  BONNE NOUVELLE","75010",doubleArrayOf(48.86811300034083,2.329704999637536),false)
        // the following locations do not have accurate geo localisation
        val shoot4 = ShootingLocation("2019-43", Date(2019,1,1), "T??l??film", "Des r??ves au-dessus de leur t??te","Arnaud S??lignac","SON ET LUMIERE","20 esplanade nathalie sarraute, 75018 paris","75018",doubleArrayOf(48.80000087147879,2.012480627818585),false)
        val shoot5 = ShootingLocation("2019-536", Date(2019,1,1), "S??rie TV", "The Eddy","Damien Chazelle","atlantique productions","place de la porte d'auteuil, 75016 paris","75016",doubleArrayOf(48.84560087999879,2.303550627818585),false)
        val shoot6 = ShootingLocation("2017-363", Date(2017,1,1), "S??rie Web", "MIAMI VINE","Cl??ment Pillet et Julien Arnardi","CITIZEN PRODUCTIONS","rue du rocher, 75008 paris","75008",doubleArrayOf(48.84000087147879,2.303550627818585),false)
        val shoot7 = ShootingLocation("2017-360", Date(2017,1,1), "S??rie Web", "MIAMI VINE","Cl??ment Pillet et Julien Arnardi","CITIZEN PRODUCTIONS","rue de vienne, 75008 paris","75008",doubleArrayOf(48.87000087147879,2.203550627818585),false)
        locations.addShootingLocation(shoot)
        locations.addShootingLocation(shoot2)
        locations.addShootingLocation(shoot3)
        locations.addShootingLocation(shoot4)
        locations.addShootingLocation(shoot5)
        locations.addShootingLocation(shoot6)
        locations.addShootingLocation(shoot7)
        locations.addShootingLocation(shoot8)

        displayListFragment()
    }

    /**
     * Launch the detail activity displaying the details of a location (executed when a location has been clicked on in the list)
     * A location is sent to the started activity
     */
    fun startDetailActivity(id: String, fav: Boolean) {
        val intent = Intent(this, DetailActivity::class.java)
        locations = locations.updateFavourites(locations,id,fav)
        val shootingLocation = locations.getShootingLocationById(id)
        intent.putExtra("location", shootingLocation)
        startForResult.launch(intent)
    }

    /**
     * Starts the main activity while expecting a location from the finished activity (detail activity)
     */
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val changedLocation = result.data?.getSerializableExtra(FAVOURITE_MODIFICATION) as ShootingLocation
            locations = locations.updateFavourites(locations,changedLocation.locationId, changedLocation.favourite)
            // updateRemoteData(changedLocation.locationId, changedLocation.favourite)
            locationAdapter.refreshData(locations.getAllShootingLocations())
            locationAdapter.notifyDataSetChanged()
            displayListFragment()
        }
    }

    /**
     * Displays ListFragment in the FrameLayout
     */
    private fun displayListFragment() {
        val listFragment = ListFragment.newInstance(locations.getAllShootingLocations())
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, listFragment)
            .commit()
        locationAdapter = LocationAdapter(locations.getAllShootingLocations(),listFragment)
    }

    /**
     * Displays AppInfoFragment in the FrameLayout
     */
    private fun displayAppInfoFragment() {
        val appInfoFragment = AppInfoFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, appInfoFragment)
            .commit()
    }

    /**
     * Displays MapsFragment in the FrameLayout
     */
    private fun displayMapFragment() {
        val mapFragment = MapsFragment.newInstance(locations)
        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_frame_layout, mapFragment)
            .commit()
    }

    /**
     * Inflates the menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    /**
     * Handle the menu options to navigate between the list, map and app info fragments as well as a refresh option
     */
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
}