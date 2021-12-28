package com.example.fst_cloud_new.HOMEPAGE

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.fst_cloud_new.FOOD.FoodFragment
import com.example.fst_cloud_new.MAPS.Map_data_holder_activity
import com.example.fst_cloud_new.PROFILES.About
import com.example.fst_cloud_new.PROFILES.Contact
import com.example.fst_cloud_new.PROFILES.PrivacyPolicy
import com.example.fst_cloud_new.PROFILES.Setting
import com.example.fst_cloud_new.Profile_Data.Profile_Model
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SHOP.ShopFragment
import com.example.fst_cloud_new.Start_Pages.FSTRegisterPage
import com.example.fst_cloud_new.TRAVEL.TravelFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty
import java.io.IOException


class HOMEPAGE : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    companion object  {

        const val NAME = "NAME"
    }

    var PICK_IMAGE_REQUEST = 22
    lateinit var filepath: Uri
    lateinit var navigationView : NavigationView
    lateinit var nav_header_image : ImageView
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        supportActionBar?.hide()


        Toasty.normal(this, "Welcome to FST",
            Toast.LENGTH_LONG, ContextCompat.getDrawable(this, R.drawable.logoo)).show();

        auth = FirebaseAuth.getInstance()
        navigationView = findViewById(R.id.nav_menu)


        var head = navigationView.getHeaderView(0)

       nav_header_image  = head.findViewById(R.id.nav_header_img)

        var nav_header_name : TextView = head.findViewById(R.id.nav_header_name)


        nav_header_image.setOnClickListener {

            chooseImage()
        }

//        val name=intent.getStringExtra("name")
//        val image = intent.getIntExtra("image",1)

        //nav_header_name.text = name
        //descdescription.text = description
        //detailimage.setImageResource(image)

        navigationView.setNavigationItemSelectedListener(this)

        var toolbar =  findViewById<Toolbar>(R.id.toolbar)

        var drawerlayout=findViewById<DrawerLayout>(R.id.drawerlayout)

        setSupportActionBar(toolbar)


        val togglebar = ActionBarDrawerToggle(this, drawerlayout,toolbar,R.string.open,R.string.close)

        togglebar.isDrawerIndicatorEnabled=true
        drawerlayout.addDrawerListener(togglebar)
        togglebar.syncState()



        val viewpager : ViewPager = findViewById(R.id.viewPager)


        val locationn : ImageView = findViewById(R.id.locationn)
      //  val search : ImageView = findViewById(R.id.main_search)


        locationn.setOnClickListener{
            intent = Intent(this, Map_data_holder_activity::class.java)
            startActivity(intent)
        }


//        search.setOnClickListener{
//            intent = Intent(this, Searching_User::class.java)
//            startActivity(intent)
//        }



        val tab : TabLayout = findViewById(R.id.tab)


        setupViewPager(viewpager)
        tab.setupWithViewPager(viewpager)


    }

    private fun setupViewPager(viewpager: ViewPager) {
        var adapter = ViewPagerAdapter(supportFragmentManager)

        // LoginFragment is the name of Fragment and the Login
        // is a title of tab
        adapter.addFragment(FoodFragment(this), "Restaurant")
        adapter.addFragment(ShopFragment(this), "Shop")
        adapter.addFragment(TravelFragment(this), "Travel")



        // setting adapter to view pager.
        viewpager.setAdapter(adapter)
    }


    class ViewPagerAdapter : FragmentPagerAdapter {


        private final var fraglist : ArrayList<Fragment> = ArrayList()
        private final var fragtitle: ArrayList<String> = ArrayList()

        // this is a secondary constructor of ViewPagerAdapter class.
        constructor(supportFragmentManager: FragmentManager)
                : super(supportFragmentManager)

        // returns which item is selected from arraylist of fragments.
        override fun getItem(position: Int): Fragment {
            return fraglist.get(position)
        }

        // returns which item is selected from arraylist of titles.

        override fun getPageTitle(position: Int): CharSequence {
            return fragtitle.get(position)
        }

        // returns the number of items present in arraylist.
        override fun getCount(): Int {
            return fraglist.size
        }

        // this function adds the fragment and title in 2 separate  arraylist.
        fun addFragment(fragment: Fragment, title: String) {
            fraglist.add(fragment)
            fragtitle.add(title)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var id = item.itemId
        when(id)
        {
            R.id.edit_profile -> {
//                intent = Intent(this, Profile_Dummy::class.java)
//                startActivity(intent)

                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
            }

            R.id.home -> {
                intent = Intent(this,HOMEPAGE::class.java)
                startActivity(intent)
            }

            R.id.about -> {
                intent = Intent(this, About::class.java)
                startActivity(intent)
            }

            R.id.contact -> {
                intent = Intent(this, Contact::class.java)
                startActivity(intent)
            }

            R.id.setting -> {
                intent = Intent(this, Setting::class.java)
                startActivity(intent)
            }
            R.id.support -> {
                intent = Intent(this, PrivacyPolicy::class.java)
                startActivity(intent)
            }

            R.id.version -> {
                Toasty.info(this, "Version is 1.0.0", Toast.LENGTH_SHORT).show()
            }

             R.id.logout -> {


                 val builder = AlertDialog.Builder(this)
                 builder.setTitle("LOGOUT")
                 builder.setMessage("Are you sure you want to logout?")
                 builder.setIcon(R.drawable.ic_logout)


                 builder.setPositiveButton("Logout"){dialogInterface, which ->

                     auth.signOut()
                     var intent = Intent(this, FSTRegisterPage::class.java)
                     startActivity(intent)

                 }

//
//            builder.setNeutralButton("Cancel"){dialogInterface , which ->
//
//
//            }


                 builder.setNegativeButton("Cancel"){dialogInterface, which ->

                     //Toast.makeText(this,"Clicked Cancel",Toast.LENGTH_LONG).show()

                 }

                 val alertDialog : AlertDialog = builder.create()
                 alertDialog.setCancelable(false)
                 alertDialog.show()



            }



        }



        return true
    }





    override fun onBackPressed() {


        val builder = AlertDialog.Builder(this)
        builder.setTitle("EXIT")
        builder.setMessage("Are you sure you want to exit from FST?")
        builder.setIcon(R.drawable.ic_logout)


        builder.setPositiveButton("Exit"){dialogInterface, which ->


            finishAffinity()


        }


        builder.setNegativeButton("Cancel"){dialogInterface, which ->

            //Toast.makeText(this,"Clicked Cancel",Toast.LENGTH_LONG).show()

        }

        val alertDialog : AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()



    }

    private fun getUserData() {


        var dbref : DatabaseReference

        var image : String? = ""
        var name : String? = ""
        dbref = FirebaseDatabase.getInstance().getReference()
        var query : Query = dbref.child("Profile/")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){



                        val profile = snapshot.getValue(Profile_Model::class.java)
                        var profile_name = profile?.vendor_username.toString()
                    var profile_image = profile?.vendor_image.toString()

                    Toast.makeText(this@HOMEPAGE, "name = " + profile_name,  Toast.LENGTH_SHORT).show()




                }
                else
                {
                    Toasty.error(this@HOMEPAGE, "No data found!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toasty.info(this@HOMEPAGE, "Something went wrong", Toast.LENGTH_SHORT).show()


            }
        })


    }


    fun chooseImage() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST
            && resultCode == RESULT_OK
            && data != null
            && data.getData() != null
        ) {

            // Get the Uri of data
            filepath = data.getData()!!;
            try {

                // Setting image on image view using Bitmap
                var bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                nav_header_image.setImageBitmap(bitmap);
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace();
            }


        }
    }

}



