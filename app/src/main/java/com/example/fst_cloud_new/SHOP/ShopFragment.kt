package com.example.fst_cloud_new.SHOP

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.FOOD.horizontal_recycle_fragment_food_adapter
import com.example.fst_cloud_new.FOOD.horizontal_recycle_fragment_food_model
import com.example.fst_cloud_new.RESTAURANT.Restaurant_Adapter
import com.example.fst_cloud_new.RESTAURANT.Restaurant_Model
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty

class ShopFragment (context: Context): Fragment() {

    var food_context = context
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var hadapter: RecyclerView.Adapter<horizontal_recycle_fragment_shop_adapter.ViewHolder>? = null
    private var vlayoutManager: RecyclerView.LayoutManager? = null
    private var vadapter: RecyclerView.Adapter<vertical_recycle_fragment_shop_adapter.ViewHolder>? = null

    private lateinit var shopArrayList : ArrayList<Shop_Model>

    private lateinit var vrecycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v : View = LayoutInflater.from(food_context).inflate(
            R.layout.shop_fragment_fst,
            container,false)


        val items = ArrayList<horizontal_recycle_fragment_shop_model>()

        vrecycleView = v.findViewById(R.id.verticalrecycleViewshop)


        items.add(
            horizontal_recycle_fragment_shop_model(
                "Khadi",
                R.drawable.s1
            )
        )
        items.add(
            horizontal_recycle_fragment_shop_model(
                "Nishat Lilan",
                R.drawable.s2
            )
        )
        items.add(
            horizontal_recycle_fragment_shop_model(
                "Kids Store",
                R.drawable.s3
            )
        )

        items.add(
            horizontal_recycle_fragment_shop_model(
                "Cantt Store",
                R.drawable.s4
            )
        )
        items.add(
            horizontal_recycle_fragment_shop_model(
                "Hush puppies",
                R.drawable.s5
            )
        )
        items.add(
            horizontal_recycle_fragment_shop_model(
                "Stone Harbor",
                R.drawable.s6
            )
        )


        // layoutManager = LinearLayoutManager(this)

        val recycleView: RecyclerView = v.findViewById(R.id.horizontalrecycleViewshop)

//        recycleView.setOnClickListener {
//            var intent = Intent(context, ShopMainPageFST::class.java)
//            startActivity(intent)
//        }


        recycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.HORIZONTAL, false)

        hadapter = horizontal_recycle_fragment_shop_adapter(items, food_context)
        recycleView.adapter = hadapter



        val vitems = ArrayList<Shop_Model>()

//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Khadi", "located in bazar", "3 min | 4.2 stars",
//                R.drawable.s7
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Stone Harbor", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s8
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Outfitters", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s9
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Engine jeans", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s10
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Nishat Lilan", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s1
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Khadi", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s2
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Guldberg", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s3
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_fragment_shop_model(
//                "Kids Store", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s4
//            )
//        )
       // val vrecycleView: RecyclerView = v.findViewById(R.id.verticalrecycleViewshop)


        vrecycleView.layoutManager = LinearLayoutManager(food_context,
            LinearLayoutManager.VERTICAL, false)

//        vadapter = vertical_recycle_fragment_shop_adapter(vitems, food_context)
//        vrecycleView.adapter = vadapter

        val DividerItemDecoration = DividerItemDecoration(food_context, DividerItemDecoration.VERTICAL)
        vrecycleView.addItemDecoration(DividerItemDecoration)


        getUserData()

        return v
    }

    private fun getUserData() {
        shopArrayList = arrayListOf<Shop_Model>()

        var dbref : DatabaseReference

        var dish_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Shop/")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {

                        val shop = userSnapShot.getValue(Shop_Model::class.java)
                        dish_name = userSnapShot.child("shop_name").value.toString()
                        shopArrayList.add(shop!!)
                    }
                    vrecycleView.adapter = Shop_Adapter(shopArrayList,food_context)

                }
                else
                {
                    Toasty.error(food_context, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(food_context, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        })


    }

}


