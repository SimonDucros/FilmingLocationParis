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

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://app-a420d8b0-91fa-4e22-aa10-d7b502ac5499.cleverapps.io"

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
        remoteDataFetching()
    //    localTests()
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
        TODO test update remote locations
         */

        shootingLocationService.updateFavourite(locationId,favourite)
            .enqueue(object : Callback<ShootingLocation> {
                override fun onResponse(
                    call: Call<ShootingLocation>,
                    response: Response<ShootingLocation>
                ) {
                    var place = response.body()
                    if (place != null) {
                        locations.updateFavourites(locations,place.id_lieu, place.favourite)
                    }
                    displayListFragment()
                    Log.i("Update Favourites","response is null")
                    }
                override fun onFailure(call: Call<ShootingLocation>, t: Throwable) {
                    Toast.makeText(applicationContext,"Cannot update favourites", Toast.LENGTH_SHORT).show()
                }
            })



    }

    /**
     * Tests using the local version of the project
     */
    fun localTests(){
 /*       val shoot2 = ShootingLocation("2019-1719", Date(2019,1,1), "Long métrage", "30 Jours Max","Tarek BOUDALI","AXEL FILMS PRODUCTION","rue rené clair, 75018 paris","75018",doubleArrayOf(48.87219487147879,2.303550627818585),false)
        val shoot = ShootingLocation("2019-1712", Date(2019,1,1), "Long métrage", "CIGARE AU MIEL","Madame KAMIR AÏNOUZ","ELIPH PRODUCTIONS","7 rue de berri, 75008 paris","75008",doubleArrayOf(48.87200007147879,2.303550000018585),false)
        val shoot3 = ShootingLocation("2016-605", Date(2016,1,1), "Série TV", "LIANG SHENG","LIU CHUN-CHIEN","KANZAMAN FRANCE","RUE  DU BEARN","75003",doubleArrayOf(48.87000087147879,2.303550627818585),false)
        val shoot4 = ShootingLocation("2019-43", Date(2019,1,1), "Téléfilm", "Des rêves au-dessus de leur tête","Arnaud Sélignac","SON ET LUMIERE","20 esplanade nathalie sarraute, 75018 paris","75018",doubleArrayOf(48.87000087147879,2.303550627818585),false)
        val shoot5 = ShootingLocation("2019-536", Date(2019,1,1), "Série TV", "The Eddy","Damien Chazelle","atlantique productions","place de la porte d'auteuil, 75016 paris","75016",doubleArrayOf(48.87000087147879,2.303550627818585),false)
        val shoot6 = ShootingLocation("2017-363", Date(2017,1,1), "Série Web", "MIAMI VINE","Clément Pillet et Julien Arnardi","CITIZEN PRODUCTIONS","rue du rocher, 75008 paris","75008",doubleArrayOf(48.87000087147879,2.303550627818585),false)
        val shoot7 = ShootingLocation("2017-360", Date(2017,1,1), "Série Web", "MIAMI VINE","Clément Pillet et Julien Arnardi","CITIZEN PRODUCTIONS","rue de vienne, 75008 paris","75008",doubleArrayOf(48.87000087147879,2.303550627818585),false)
        locations.addShootingLocation(shoot)
        locations.addShootingLocation(shoot2)
        locations.addShootingLocation(shoot3)
        locations.addShootingLocation(shoot4)
        locations.addShootingLocation(shoot5)
        locations.addShootingLocation(shoot6)
        locations.addShootingLocation(shoot7)

        */
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
            locations = locations.updateFavourites(locations,changedLocation.id_lieu, changedLocation.favourite)
            updateRemoteData(changedLocation.id_lieu, changedLocation.favourite)
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