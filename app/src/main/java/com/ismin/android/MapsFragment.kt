package com.ismin.android

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Can be displayed in the recycling viewer of the main activity
 * Map displays the locations via pins
 */
class MapsFragment : Fragment() {
    private lateinit var listLocations: ListShootingLocations

    /**
     * Displays all locations on a map and moves the camera to the last location
     */
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        var geoData = LatLng(-34.0, 151.0)
        for(location in listLocations.getAllShootingLocations()){
            geoData = LatLng(location.geo_point_2d[0],location.geo_point_2d[1])
            googleMap.addMarker(MarkerOptions().position(geoData).title(location.nom_tournage))
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(geoData))
    }

    /**
     * Creation of the view
     */
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    /**
     * Filling the created view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    /**
     * Companion object retrieving the location list sent by the main activity
     */
    companion object {
        @JvmStatic
        fun newInstance(locations: ListShootingLocations) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    listLocations = locations
                }
            }
    }
}