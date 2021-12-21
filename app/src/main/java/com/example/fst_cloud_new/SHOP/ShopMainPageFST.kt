package com.example.fst_cloud_new.SHOP

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.Dish_Main_Page.Dish_Main_page_Adapter
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.MAPS.Map_User
import com.example.fst_cloud_new.Main_Page_Adapter.Main_horizontal_card_adapter
import com.example.fst_cloud_new.Main_Page_Adapter.Main_horizontal_list_adapter
import com.example.fst_cloud_new.Main_Page_Adapter.Main_vertical_adapter
import com.example.fst_cloud_new.Main_Page_Model.Main_horizontal_card_model
import com.example.fst_cloud_new.Main_Page_Model.Main_horizontal_list_model
import com.example.fst_cloud_new.Main_Page_Model.Main_vertical_model
import com.example.fst_cloud_new.SEARCH.Searching_User
import com.example.fst_cloud_new.Vendor_Dish.Vendor_Dish_Model
import com.example.fst_cloud_new.Vendor_Shop_Category.Vendor_Shop_Category_Adapter
import com.example.fst_cloud_new.Vendor_Shop_Category.Vendor_Shop_Category_Model
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty

class ShopMainPageFST : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<Main_horizontal_list_adapter.ViewHolder>? = null
    private var vlayoutManager: RecyclerView.LayoutManager? = null
    private var vadapter: RecyclerView.Adapter<Main_vertical_adapter.ViewHolder>? = null
    private var hadapter: RecyclerView.Adapter<Main_horizontal_card_adapter.ViewHolder>? = null

    lateinit var vrecycleView: RecyclerView
    private lateinit var dbref : DatabaseReference
    private lateinit var DishArrayList : ArrayList<Vendor_Shop_Category_Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_main_page_fst)

        supportActionBar?.hide()
DishArrayList = ArrayList()
        val back : ImageView = findViewById(R.id.back)
        val location : ImageView = findViewById(R.id.location)
        val shop_search : ImageView = findViewById(R.id.shop_search)

        vrecycleView = findViewById(R.id.shopverticalfood)

        back.setOnClickListener{
            intent = Intent(this, HOMEPAGE::class.java)
            startActivity(intent)
        }


        location.setOnClickListener{
            intent = Intent(this, Map_User::class.java)
            startActivity(intent)
        }

        shop_search.setOnClickListener{
            intent = Intent(this, Searching_User::class.java)
            startActivity(intent)
        }

        val items = ArrayList<Main_horizontal_list_model>()


        items.add(
            Main_horizontal_list_model(
                "Jeans",
                R.drawable.s1
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Shirts",
                R.drawable.s2
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Pents",
                R.drawable.s3
            )
        )

        items.add(
            Main_horizontal_list_model(
                "Hats",
                R.drawable.s4
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Harbour",
                R.drawable.s5
            )
        )
        items.add(
            Main_horizontal_list_model(
                "Coats",
                R.drawable.s6
            )
        )


        items.add(
            Main_horizontal_list_model(
                "Jeans",
                R.drawable.s7
            )
        )
      
        items.add(
            Main_horizontal_list_model(
                "Jackets",
                R.drawable.s9
            )
        )

        items.add(
            Main_horizontal_list_model(
                "Hats",
                R.drawable.s10
            )
        )

        // layoutManager = LinearLayoutManager(this)

        val recycleView: RecyclerView = findViewById(R.id.foodhorizontallist)
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = Main_horizontal_list_adapter(items, this)

        recycleView.adapter = adapter



        val hitems = ArrayList<Main_horizontal_card_model>()


        hitems.add(
            Main_horizontal_card_model(
                "Jeans",
                R.drawable.s1
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Hats",
                R.drawable.s4
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Clothes",
                R.drawable.s5
            )
        )

        hitems.add(
            Main_horizontal_card_model(
                "Jewelery",
                R.drawable.s6
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Coats",
                R.drawable.s7
            )
        )
        hitems.add(
            Main_horizontal_card_model(
                "Jeans",
                R.drawable.s8
            )
        )


        // layoutManager = LinearLayoutManager(this)

        val recycleViewcard: RecyclerView = findViewById(R.id.foodhorizontalcard)
        recycleViewcard.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        hadapter = Main_horizontal_card_adapter(hitems, this)

        recycleViewcard.adapter = hadapter

        val vitems = ArrayList<Main_vertical_model>()

//        vitems.add(
//            Main_vertical_model(
//                "Jeans", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s7
//            )
//        )
//
//        vitems.add(
//            Main_vertical_model(
//                "Hatss", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s8
//            )
//        )
//
//        vitems.add(
//            Main_vertical_model(
//                "Nishat Lilan", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s3
//            )
//        )
//
//        vitems.add(
//            Main_vertical_model(
//                "Jackets", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s5
//            )
//        )
//
//        vitems.add(
//            Main_vertical_model(
//                "Bata", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s7
//            )
//        )
//
//        vitems.add(
//            Main_vertical_model(
//                "Pents", "North Food special plate", "3 min | 4.2 stars",
//                R.drawable.s9
//            )
//        )
//


        vrecycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        vadapter = Main_vertical_adapter(vitems, this)
//        vrecycleView.adapter = vadapter

        val DividerItemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        vrecycleView.addItemDecoration(DividerItemDecoration)

        getUserData()
    }



    fun getUserData(){

        var dish_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Shopmain/")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {
                        val dish = userSnapShot.getValue(Vendor_Shop_Category_Model::class.java)
                        dish_name = userSnapShot.child("shop_main_name").value.toString()
                        DishArrayList.add(dish!!)
                    }
                    vrecycleView.adapter = Vendor_Shop_Category_Adapter(this@ShopMainPageFST,
                        DishArrayList)

                }
                else
                {
                    Toasty.error(this@ShopMainPageFST, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@ShopMainPageFST, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        })


    }

}



