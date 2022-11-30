package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val LOCATION = "shootingLocations"

class ListFragment : Fragment() {
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var rcvLocations: RecyclerView
    private var locations: ArrayList<ShootingLocation> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            locations = it.getSerializable(LOCATION) as ArrayList<ShootingLocation>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        locationAdapter = LocationAdapter(locations)

        rcvLocations = rootView.findViewById(R.id.f_list_rcv_locations)
        rcvLocations.adapter = locationAdapter

        val linearLayoutManager = LinearLayoutManager(context)
        rcvLocations.layoutManager = linearLayoutManager

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(books: ArrayList<ShootingLocation>) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LOCATION, books)
                }
            }
    }
}