package com.example.fst_cloud_new.Compare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty

class showCompareable_shop : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var DishRecycleview : RecyclerView
    private lateinit var ShopArrayList : ArrayList<compare_model_shop>
    private lateinit var vendor_dish_search : ImageView
    lateinit var  recycleView: RecyclerView

    var database_name : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_compareable_shop)



        ShopArrayList = arrayListOf<compare_model_shop>()

        recycleView = findViewById(R.id.comapre_recycleview)

        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        val DividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycleView.addItemDecoration(DividerItemDecoration)




        getUserData()



    }

    fun getUserData(){




        var dish_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Shopmain")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {
                        val dish = userSnapShot.getValue(compare_model_shop::class.java)

                        ShopArrayList.add(dish!!)
                    }

//                    DishArrayList.sortByDescending {
//                        it.dish_price
//                    }

                    recycleView.adapter = Compare_Shop_Adapter(this@showCompareable_shop,ShopArrayList)
                }
                else
                {
                    Toasty.error(this@showCompareable_shop, "Snapshot does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@showCompareable_shop, "Something went wrong" + error.details, Toast.LENGTH_SHORT).show()
            }
        })


    }



}