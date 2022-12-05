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

class MapsFragment : Fragment() {
    private lateinit var listLocations: ListShootingLocations

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

        // display all locations on the maps
        var geoData = LatLng(-34.0, 151.0)
        for(location in listLocations.getAllShootingLocations()){
            geoData = LatLng(location.geoLocation[0],location.geoLocation[1])
            googleMap.addMarker(MarkerOptions().position(geoData).title(location.title))
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(geoData))

    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
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