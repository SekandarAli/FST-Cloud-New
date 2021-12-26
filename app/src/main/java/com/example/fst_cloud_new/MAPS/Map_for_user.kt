package com.example.fst_cloud_new.MAPS

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.databinding.ActivityMapForUserBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty


class Map_for_user : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapForUserBinding
    private lateinit var dbref : DatabaseReference
    var latitude : ArrayList<String> = arrayListOf()

     var longitude : ArrayList<String> = arrayListOf()
     var title : ArrayList<String> = arrayListOf()
    var latit = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMapForUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val intent = getIntent().extras

         latitude = (intent!!.getSerializable("latitude") as ArrayList<String>?)!!
         longitude = (intent!!.getSerializable("longitude") as ArrayList<String>?)!!
         title = (intent!!.getSerializable("title") as ArrayList<String>?)!!



    }







    override fun onMapReady(googleMap: GoogleMap) {




        mMap = googleMap


        for (i in 0..latitude.size-1) {

            var markerOptions =MarkerOptions()

                markerOptions.position(LatLng(latitude[i].toDouble(), longitude[i].toDouble()))
                markerOptions.anchor(0.5f, 0.5f)
                markerOptions.title(title[i]).snippet("Restaurant")

mMap.setMaxZoomPreference(15.0f)
            val m1 : Marker? = googleMap.addMarker(markerOptions)
            if (m1 != null) {
                m1.showInfoWindow()
            }

        }

    }

}