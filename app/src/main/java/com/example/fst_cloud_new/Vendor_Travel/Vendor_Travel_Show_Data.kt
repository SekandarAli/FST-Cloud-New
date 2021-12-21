package com.example.fst_cloud_new.Vendor_Travel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SEARCH.Searching_Vendor
import com.google.firebase.database.*

class Vendor_Travel_Show_Data: AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var TravelRecycleview : RecyclerView
    private lateinit var TravelArrayList : ArrayList<Vendor_Travel_Model>
    private lateinit var vendor_travel_search : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_travel_show_data)




        //var vendor_Travel_recyclerview_back : Button = findViewById(R.id.vendor_Travel_recyclerview_back)


        TravelRecycleview = findViewById(R.id.vendor_travel_recyclerview)
        TravelRecycleview.layoutManager = LinearLayoutManager(this)
        TravelRecycleview.setHasFixedSize(true)

        vendor_travel_search = findViewById(R.id.vendor_travel_search)

        TravelArrayList = arrayListOf<Vendor_Travel_Model>()

//        vendor_Travel_recyclerview_back.setOnClickListener {
//
//            intent = Intent(this, Vendor_Travel_Add_Data::class.java)
//            startActivity(intent)
//        }


        vendor_travel_search.setOnClickListener {

            intent = Intent(this, Searching_Vendor::class.java)
            startActivity(intent)

        }

        getUserData()
    }

    fun getUserData(){

        var Travel_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Travel/")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {
                        val Travel = userSnapShot.getValue(Vendor_Travel_Model::class.java)
                        Travel_name = userSnapShot.child("travel_name").value.toString()
                        TravelArrayList.add(Travel!!)
                    }
                    TravelRecycleview.adapter =
                        Vendor_Travel_Adapter(this@Vendor_Travel_Show_Data,TravelArrayList)

                }
                else
                {
                    Toast.makeText(this@Vendor_Travel_Show_Data, "snapshot does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Vendor_Travel_Show_Data, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })


    }



}
