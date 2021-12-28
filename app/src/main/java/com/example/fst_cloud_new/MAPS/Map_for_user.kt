package com.example.fst_cloud_new.MAPS

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.databinding.ActivityMapForUserBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.database.*


class Map_for_user : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapForUserBinding
    private lateinit var dbref : DatabaseReference


    var latitude : ArrayList<String> = arrayListOf()
    var longitude : ArrayList<String> = arrayListOf()
     var title : ArrayList<String> = arrayListOf()
    var latit = ""

    var shop_latitude : ArrayList<String> = arrayListOf()
    var shop_longitude : ArrayList<String> = arrayListOf()
    var shop_title : ArrayList<String> = arrayListOf()
    var shop_latit = ""


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

//Shop data
        shop_latitude = (intent!!.getSerializable("shop_latitude") as ArrayList<String>?)!!
        shop_longitude = (intent!!.getSerializable("shop_longitude") as ArrayList<String>?)!!
        shop_title = (intent!!.getSerializable("shop_title") as ArrayList<String>?)!!

        Toast.makeText(this, "shop_latitude = " + shop_latitude.size, Toast.LENGTH_SHORT).show()



    }







    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap


        for (i in 0..shop_latitude.size-1) {
            var markerOptions =MarkerOptions()

            var location : LatLng = LatLng(shop_latitude[i].toDouble(),shop_longitude[i].toDouble())

            markerOptions.position(location)

            markerOptions.title(shop_title[i]).snippet("shop")
           markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))



            val cameraPosition = CameraPosition.Builder()
                .target(location).zoom(13f).build()
            googleMap.animateCamera(
                CameraUpdateFactory
                    .newCameraPosition(cameraPosition)
            )


            googleMap.addMarker(markerOptions)

        }




        for (i in 0..latitude.size-1) {
            var markerOptions =MarkerOptions()

            var location : LatLng = LatLng(latitude[i].toDouble(),longitude[i].toDouble())

                markerOptions.position(location)

                markerOptions.title(title[i]).snippet("Restaurant")


            val cameraPosition = CameraPosition.Builder()
                .target(location).zoom(10f).build()
            googleMap.animateCamera(
                CameraUpdateFactory
                    .newCameraPosition(cameraPosition)
            )


            googleMap.addMarker(markerOptions)

        }



    }

}