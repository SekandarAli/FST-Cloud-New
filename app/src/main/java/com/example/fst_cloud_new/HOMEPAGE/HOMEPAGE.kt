package com.example.fst_cloud_new.HOMEPAGE

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.fst_cloud_new.FOOD.FoodFragment
import com.example.fst_cloud_new.MAPS.Map_User
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SEARCH.Searching_User
import com.example.fst_cloud_new.SHOP.ShopFragment
import com.example.fst_cloud_new.Start_Pages.FSTRegisterPage
import com.example.fst_cloud_new.TRAVEL.TravelFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import es.dmoral.toasty.Toasty
import kotlin.system.exitProcess


class HOMEPAGE : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        supportActionBar?.hide()

        Toasty.normal(this,"Welcome to FST",Toast.LENGTH_LONG).show()



        var navigationView = findViewById<NavigationView>(R.id.nav_menu)

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
        val search : ImageView = findViewById(R.id.main_search)


        locationn.setOnClickListener{
            intent = Intent(this, Map_User::class.java)
            startActivity(intent)
        }


        search.setOnClickListener{
            intent = Intent(this, Searching_User::class.java)
            startActivity(intent)
        }



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
            R.id.home -> {
                Toasty.info(this, "Home Clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.about -> {
                Toasty.info(this, "About Clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.contact -> {
                Toasty.info(this, "contact Clicked", Toast.LENGTH_SHORT).show()
            }

            R.id.setting -> {
                Toasty.info(this, "Setting Clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.support -> {
                Toasty.info(this, "Support Clicked", Toast.LENGTH_SHORT).show()
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


}

