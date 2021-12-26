package com.example.fst_cloud_new.Compare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fst_cloud_new.R
import com.example.fstsignin.Compare.Compare_Dish_Adapter
import com.example.fstsignin.Compare.Compare_Model
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty

class ShoeCompareAble : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var DishRecycleview : RecyclerView
    private lateinit var DishArrayList : ArrayList<Compare_Model>
    private lateinit var vendor_dish_search : ImageView
    lateinit var  recycleView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoe_compare_able)


        DishArrayList = arrayListOf<Compare_Model>()

        recycleView = findViewById(R.id.comapre_recycleview)

        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        val DividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycleView.addItemDecoration(DividerItemDecoration)


        getUserData()



    }

    fun getUserData(){

        var category_name = "burger"


        var dish_name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Dish/")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapShot in snapshot.children)
                    {
                        val dish = userSnapShot.getValue(Compare_Model::class.java)
                        dish_name = userSnapShot.child("dish_name").value.toString()
                        DishArrayList.add(dish!!)
                    }

                    DishArrayList.sortByDescending {
                        it.dish_price
                    }

                    recycleView.adapter = Compare_Dish_Adapter(this@ShoeCompareAble,DishArrayList)
                }
                else
                {
                    Toasty.error(this@ShoeCompareAble, "Snapshot does not exist", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@ShoeCompareAble, "Something went wrong" + error.details, Toast.LENGTH_SHORT).show()
            }
        })


    }

}



