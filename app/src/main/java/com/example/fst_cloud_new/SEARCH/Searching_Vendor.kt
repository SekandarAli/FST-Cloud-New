package com.example.fst_cloud_new.SEARCH

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Adapter
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Model
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty

class Searching_Vendor : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var DishRecycleview : RecyclerView
    private lateinit var DishArrayList : ArrayList<Vendor_Dish_Model>
    private lateinit var search_text : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searching_vendor)

        DishRecycleview = findViewById(R.id.searchData_recycleview)
        DishRecycleview.layoutManager = LinearLayoutManager(this)
        DishRecycleview.setHasFixedSize(true)

        DishArrayList = arrayListOf<Vendor_Dish_Model>()

        var tv_search_text = findViewById<AutoCompleteTextView>(R.id.ed_search_text)


        val suggestions = arrayOf("KFC","Saif Bakers","Coffee house and grill",
            "De Minister Cafe","Pizza hut \uD83D\uDED6","Mc Donald","Cafe")

        val adapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_expandable_list_item_1, suggestions)

        tv_search_text.setAdapter(adapter)
        tv_search_text.setThreshold(1)



        var btn_search = findViewById<Button>(R.id.btn_search)
        var btn_clear = findViewById<Button>(R.id.btn_clear)

        btn_clear.setOnClickListener {

            tv_search_text.text.clear()
        }



        btn_search.setOnClickListener {
            search_text = tv_search_text.text.toString()
            //Toast.makeText(this, "text = "+search_text, Toast.LENGTH_SHORT).show()
            getData()

        }

    }

    private fun getData() {



        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Restaurant").orderByChild("resturant_name").equalTo(search_text)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {
                        val dish = userSnapShot.getValue(Vendor_Dish_Model::class.java)

                        DishArrayList.add(dish!!)
                    }
                    DishRecycleview.adapter = Vendor_Dish_Adapter(this@Searching_Vendor,DishArrayList)

                }
                else
                {
                    Toasty.error(this@Searching_Vendor, "Snapshot does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.warning(this@Searching_Vendor, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })


    }
}