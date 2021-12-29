package com.example.fst_cloud_new.MAPS

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.fst_cloud_new.FOOD.FoodMainPageFST
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
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

    lateinit var travel_LatLnG : ArrayList<LatLng>



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

        //Toast.makeText(this, "Map for user = " + shop_latitude.size, Toast.LENGTH_SHORT).show()






    }





    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap



        //shop
        for (i in 0..shop_latitude.size-1) {
            var markerOptions =MarkerOptions()

            var location : LatLng = LatLng(shop_latitude[i].toDouble(),shop_longitude[i].toDouble())

            markerOptions.position(location)

            markerOptions.title(shop_title[i]).snippet("Shop")
           markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))



            val cameraPosition = CameraPosition.Builder()
                .target(location).zoom(10f).build()
            googleMap.animateCamera(
                CameraUpdateFactory
                    .newCameraPosition(cameraPosition)
            )


            googleMap.addMarker(markerOptions)

        }


//restaurant

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

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            mMap.isMyLocationEnabled = true

        }



    }

    override fun onBackPressed() {
        var intent = Intent(this, FoodMainPageFST::class.java)
        startActivity(intent)
    }
}