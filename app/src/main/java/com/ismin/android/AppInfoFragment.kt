package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Constant text displayed in the TextView 'f_app_info_url' in 'fragment_app_info.xml'
 */
private const val URL = "This application makes use of the data collected on the following link:\nhttps://opendata.paris.fr/explore/dataset/lieux-de-tournage-a-paris/information/?disjunctive.type_tournage&disjunctive.nom_tournage&disjunctive.nom_realisateur&disjunctive.nom_producteur&disjunctive.ardt_lieu"

/**
 * Constant text displayed in the TextView 'f_app_info_explanations' in 'fragment_app_info.xml'
 */
private const val EXPLANATIONS = "This application was developed for a the class 'Android development' in the third year program of the School of Mines de Saint-Etienne in the study cycle ISMIN.\nIt's purpose is to fetch data from the previously given url, display it and be able to add items to favourites. The application is displaying information concerning shooting locations in Paris that were used for a set of movies. For each location displayed in 'LIST' (containing all locations) you can obtain more detailed information by simply clicking on the item. You can refresh the list by clicking on the icon in the menu bar (top right). To add a location to favourites click on the star in the top left corner of the detail window. Do the same to remove it from your favourites. You can view the locations on a map in the 'MAP' section. Information on the application is provided in this window called 'INFO'.\nIt was developped by Simon DUCROS and Delphine GESSE."

/**
 * Constant text displayed in the TextView 'f_app_info_licence' in 'fragment_app_info.xml'
 */
private const val LICENCE = "We are using the following libraries: \n- retrofit - OKHttp\n- Volley\n- Gson\nLicence:\n- "

/**
 * Can be displayed in the recycling viewer of the main activity
 * Provides information on the app
 */
class AppInfoFragment : Fragment() {
    /**
     * No action on creation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    /**
     * Display URL, EXPLANATIONS and LICENCE in 'fragment_app_info.xml'
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_app_info, container, false)

        rootView.findViewById<TextView>(R.id.f_app_info_url).text = URL
        rootView.findViewById<TextView>(R.id.f_app_info_explanations).text = EXPLANATIONS
        rootView.findViewById<TextView>(R.id.f_app_info_licence).text = LICENCE

        return rootView
    }

    /**
     * No variables to receive
     */
    companion object {
        @JvmStatic
        fun newInstance() =
            AppInfoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}