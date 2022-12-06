package com.ismin.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Extra specifying that a list ShootingLocation is being sent from the main activity
 */
private const val LOCATION = "shootingLocations"

/**
 * Can be displayed in the recycling viewer of the main activity
 * Provides a list of locations
 */
class ListFragment : Fragment() {
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var rcvLocations: RecyclerView
    private var locations: ArrayList<ShootingLocation> = arrayListOf()

    /**
     * On creation recover the sent shootingLocation from main activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locations = it.getSerializable(LOCATION) as ArrayList<ShootingLocation>
        }
    }

    /**
     * Display the list using the LocationAdapter
     */
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        locationAdapter = LocationAdapter(locations,this)

        rcvLocations = rootView.findViewById(R.id.f_list_rcv_locations)
        rcvLocations.adapter = locationAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        rcvLocations.layoutManager = linearLayoutManager

        return rootView
    }

    /**
     * Start the detail activity (called when a location row is being clicked on)
     */
    fun startDetailActivityFromFragment(id: String, fav: Boolean) {
        (activity as MainActivity).startDetailActivity(id,fav)
    }

    /**
     * Companion object allowing to load a list of locations
     */
    companion object {
        @JvmStatic
        fun newInstance(locations: ArrayList<ShootingLocation>) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LOCATION, locations)
                }
            }
    }
}