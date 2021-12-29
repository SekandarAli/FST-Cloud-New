package com.example.fst_cloud_new.MAPS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.fst_cloud_new.FOOD.FoodMainPageFST
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty

class Map_data_holder_activity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    lateinit var latitude : ArrayList<String>
   // lateinit var latitude2 : ArrayList<String>
    lateinit var longitude : ArrayList<String>
    lateinit var title : ArrayList<String>

    //shop

    lateinit var shop_latitude : ArrayList<String>
    // lateinit var latitude2 : ArrayList<String>
    lateinit var shop_longitude : ArrayList<String>
    lateinit var shop_title : ArrayList<String>

    lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



       getUserData()
        getShopData()
      //  Toast.makeText(this, "Data activity" + shop_latitude.size, Toast.LENGTH_SHORT).show()

    }

    fun getUserData(){

        bundle = Bundle()

        latitude = ArrayList()
        longitude = ArrayList()
        title = ArrayList()
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Location").child("latitude")
        var query2 : Query = dbref.child("Location").child("longitude")
        var query3 : Query = dbref.child("Location").child("Title")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){



                    for(userSnapShot in snapshot.children)
                    {

                        val lat  = userSnapShot.getValue()
                        latitude.add(lat as String)




                    }
bundle.putStringArrayList("latitude",latitude)
                  // Toast.makeText(this@Map_data_holder_activity, "lat size =  " + latitude.size, Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toasty.error(this@Map_data_holder_activity, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@Map_data_holder_activity, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        }
        )

        //title data get

        query3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){



                    for(userSnapShot in snapshot.children)
                    {

                        val lat  = userSnapShot.getValue()
                        title.add(lat as String)




                    }
                    bundle.putStringArrayList("title",title)
                    Toast.makeText(this@Map_data_holder_activity, "lat size =  " + latitude.size, Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toasty.error(this@Map_data_holder_activity, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@Map_data_holder_activity, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        }
        )


        query2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){



                    for(userSnapShot in snapshot.children)
                    {
                        val long = userSnapShot.value
                        longitude.add(long as String)

                        Log.d("assad","longitude = "+longitude.toString())
                    }
                    bundle.putStringArrayList("longitude",longitude)
var intent = Intent(this@Map_data_holder_activity,Map_for_user::class.java)
                    intent.putExtras(bundle)
                   startActivity(intent)
                }
                else
                {
                    Toasty.error(this@Map_data_holder_activity, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@Map_data_holder_activity, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        })
        //  Toast.makeText(this@Map_for_user, "size = " + latitude2.size, Toast.LENGTH_LONG).show()




        fun returnArray(arrayList: ArrayList<String>) : ArrayList<String>

        {
            var array : ArrayList<String>
            array = arrayList
            Toast.makeText(this, "array size = " + array.size, Toast.LENGTH_SHORT).show()
            return array
        }

    }

    fun getShopData(){

        bundle = Bundle()

        shop_latitude = ArrayList()
        shop_longitude = ArrayList()
        shop_title = ArrayList()
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Shop Location").child("latitude")
        var query2 : Query = dbref.child("Shop Location").child("longitude")
        var query3 : Query = dbref.child("Shop Location").child("Title")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){



                    for(userSnapShot in snapshot.children)
                    {

                        val lat  = userSnapShot.getValue()
                        shop_latitude.add(lat as String)




                    }
                    bundle.putStringArrayList("shop_latitude",shop_latitude)
                 //   Toast.makeText(this@Map_data_holder_activity, "lat size =  " + shop_latitude.size, Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toasty.error(this@Map_data_holder_activity, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@Map_data_holder_activity, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        }
        )

        //title data get

        query3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){



                    for(userSnapShot in snapshot.children)
                    {

                        val lat  = userSnapShot.getValue()
                        shop_title.add(lat as String)




                    }
                    bundle.putStringArrayList("shop_title",shop_title)
                 //   Toast.makeText(this@Map_data_holder_activity, "lat size =  " + shop_latitude.size, Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toasty.error(this@Map_data_holder_activity, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@Map_data_holder_activity, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        }
        )


        query2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){



                    for(userSnapShot in snapshot.children)
                    {
                        val long = userSnapShot.value
                        shop_longitude.add(long as String)

                        Log.d("assad","longitude = "+longitude.toString())
                    }
                    bundle.putStringArrayList("shop_longitude",shop_longitude)
                    var intent = Intent(this@Map_data_holder_activity,Map_for_user::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
                else
                {
                    Toasty.error(this@Map_data_holder_activity, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@Map_data_holder_activity, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        })
        //  Toast.makeText(this@Map_for_user, "size = " + latitude2.size, Toast.LENGTH_LONG).show()




        fun returnArray(arrayList: ArrayList<String>) : ArrayList<String>

        {
            var array : ArrayList<String>
            array = arrayList
            Toast.makeText(this, "array size = " + array.size, Toast.LENGTH_SHORT).show()
            return array
        }

    }


}