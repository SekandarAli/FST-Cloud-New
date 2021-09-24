package com.example.fst_cloud_new.Vendor_Dish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SEARCH.Searching_User
import com.google.firebase.database.*

class Vendor_Dish_Show_Data : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var DishRecycleview : RecyclerView
    private lateinit var DishArrayList : ArrayList<Vendor_Dish_Model>
    private lateinit var vendor_dish_search : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_dish_show_data)



        var vendor_dish_recyclerview_back : Button = findViewById(R.id.vendor_dish_recyclerview_back)
        DishRecycleview = findViewById(R.id.vendor_dish_recyclerview)
        DishRecycleview.layoutManager = LinearLayoutManager(this)
        DishRecycleview.setHasFixedSize(true)

        vendor_dish_search = findViewById(R.id.vendor_dish_search)

        DishArrayList = arrayListOf<Vendor_Dish_Model>()

        vendor_dish_recyclerview_back.setOnClickListener {

            intent = Intent(this, Vendor_Dish_Add_Data::class.java)
            startActivity(intent)
        }

        vendor_dish_search.setOnClickListener {

            intent = Intent(this, Searching_User::class.java)
            startActivity(intent)

        }

        getUserData()
    }

    fun getUserData(){

        var dish_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Dish/")

        query.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {
                        val dish = userSnapShot.getValue(Vendor_Dish_Model::class.java)
                        dish_name = userSnapShot.child("dish_name").value.toString()
                        DishArrayList.add(dish!!)
                    }
                    DishRecycleview.adapter =
                        Vendor_Dish_Adapter(this@Vendor_Dish_Show_Data,DishArrayList)

                }
                else
                {
                    Toast.makeText(this@Vendor_Dish_Show_Data, "Snapshot does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Vendor_Dish_Show_Data, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

        })


    }


}