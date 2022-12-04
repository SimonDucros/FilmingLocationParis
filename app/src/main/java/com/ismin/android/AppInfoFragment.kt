package com.ismin.android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val APP_INFO = "Thank you for using our app, here's what it can do: ... nothing."

class AppInfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_app_info, container, false)

        var infoText = rootView.findViewById<TextView>(R.id.f_app_info_text)

        infoText.text = APP_INFO

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(locations: ShootingLocation) =
            AppInfoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}