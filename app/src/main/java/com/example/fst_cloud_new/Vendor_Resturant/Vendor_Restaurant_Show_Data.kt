package com.example.fst_cloud_new.Vendor_Resturant

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

class Vendor_Restaurant_Show_Data : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var ResturantRecycleview : RecyclerView
    private lateinit var ResturantArrayList : ArrayList<Vendor_Resturant_Model>
    private lateinit var vendor_restaurant_search : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_resturant_show_data)




        //var vendor_resturant_recyclerview_back : Button = findViewById(R.id.vendor_resturant_recyclerview_back)


        ResturantRecycleview = findViewById(R.id.vendor_resturant_recyclerview)
        ResturantRecycleview.layoutManager = LinearLayoutManager(this)
        ResturantRecycleview.setHasFixedSize(true)

        vendor_restaurant_search = findViewById(R.id.vendor_restaurant_search)

        ResturantArrayList = arrayListOf<Vendor_Resturant_Model>()

//        vendor_resturant_recyclerview_back.setOnClickListener {
//
//            intent = Intent(this, Vendor_Resturant_Add_Data::class.java)
//            startActivity(intent)
//        }


        vendor_restaurant_search.setOnClickListener {

            intent = Intent(this, Searching_Vendor::class.java)
            startActivity(intent)

        }

        getUserData()
    }

    fun getUserData(){

        var resturant_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Restaurant/")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {
                        val resturant = userSnapShot.getValue(Vendor_Resturant_Model::class.java)
                        resturant_name = userSnapShot.child("restaurant_name").value.toString()
                        ResturantArrayList.add(resturant!!)
                    }
                    ResturantRecycleview.adapter =
                        Vendor_Resturant_Adapter(this@Vendor_Restaurant_Show_Data,ResturantArrayList)

                }
                else
                {
                    Toast.makeText(this@Vendor_Restaurant_Show_Data, "snapshot does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Vendor_Restaurant_Show_Data, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })


    }



}
