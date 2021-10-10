


package com.example.fst_cloud_new.FOOD

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
import com.example.fst_cloud_new.RESTAURANT.Restaurant_Adapter
import com.example.fst_cloud_new.RESTAURANT.Restaurant_Model
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SHOP.vertical_recycle_fragment_shop_adapter
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty

class FoodFragment (context: Context): Fragment() {

    var food_context = context
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var foodAdapter: RecyclerView.Adapter<horizontal_recycle_fragment_food_adapter.ViewHolder>? = null
    private var vlayoutManager: RecyclerView.LayoutManager? = null
    private var vadapter: RecyclerView.Adapter<vertical_recycle_fragment_shop_adapter.ViewHolder>? = null

    private lateinit var restaurantArrayList : ArrayList<Restaurant_Model>

    private lateinit var vrecycleView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v : View = LayoutInflater.from(food_context).inflate(
            R.layout.food_fragment_fst,
            container,false)

        val items = ArrayList<horizontal_recycle_fragment_food_model>()

        vrecycleView = v.findViewById(R.id.verticalrecycleViewfood)


        items.add(
            horizontal_recycle_fragment_food_model(
                "Saif Bakers",
                R.drawable.f10
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "Food Plannet",
                R.drawable.f9
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "De'Mininster cafe",
                R.drawable.f8
            )
        )

        items.add(
            horizontal_recycle_fragment_food_model(
                "Saif Bakers",
                R.drawable.f7
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "Food Plannet",
                R.drawable.f6
            )
        )
        items.add(
            horizontal_recycle_fragment_food_model(
                "De'Mininster cafe",
                R.drawable.r5
            )
        )


        // layoutManager = LinearLayoutManager(this)

        val recycleView: RecyclerView = v.findViewById(R.id.horizontalrecycleViewfood)
        recycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.HORIZONTAL, false)

        foodAdapter = horizontal_recycle_fragment_food_adapter(items, food_context)
        recycleView.adapter = foodAdapter








        val vitems = ArrayList<Restaurant_Model>()



//        vitems.add(
//            vertical_recycle_main_food_model(
//                "De'Mininster cafe", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.r6
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_main_food_model(
//                "Coffity", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.r7
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_main_food_model(
//                "Mir Bakers", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.r8
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_main_food_model(
//                "Usmania Resturant", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.f10
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_main_food_model(
//                "De'Mininster cafe", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.f6
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_main_food_model(
//                "Mir Bakers", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.r9
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_main_food_model(
//                "Usmania Resturant", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.f7
//            )
//        )
//
//        vitems.add(
//            vertical_recycle_main_food_model(
//                "De'Mininster cafe", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.f8
//            )
//        )



//        val vrecycleView: RecyclerView = v.findViewById(R.id.restaurant_vertical_recycleView)
        vrecycleView.layoutManager = LinearLayoutManager(food_context, LinearLayoutManager.VERTICAL, false)

//        vadapter = vertical_recycle_main_food_adapter(vitems, food_context)
//        vrecycleView.adapter = vadapter

        val DividerItemDecoration = DividerItemDecoration(food_context, DividerItemDecoration.VERTICAL)
        vrecycleView.addItemDecoration(DividerItemDecoration)

        getUserData()

        return v
    }


    private fun getUserData() {
        restaurantArrayList = arrayListOf<Restaurant_Model>()

        var dbref : DatabaseReference

        var dish_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Restaurant/")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {

                        val restaurant = userSnapShot.getValue(Restaurant_Model::class.java)
                        dish_name = userSnapShot.child("resturant_name").value.toString()
                        restaurantArrayList.add(restaurant!!)
                    }
                    vrecycleView.adapter = Restaurant_Adapter(restaurantArrayList,food_context)

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



