package com.example.fst_cloud_new.SIGN_IN

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.ADMIN.AdminPanel
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.SIGN_UP.FST_Vendor_Signup
import com.example.fst_cloud_new.Start_Pages.FSTForgetpassword
import com.example.fst_cloud_new.VENDOR.Vendor_Main_Page
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty

class FST_Vendor_Signin : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fst_vendor_signin)

        supportActionBar?.hide()

        auth = Firebase.auth

        val vendor_login : Button = findViewById(R.id.vendor_login)
        val vendor_forgetpassword : TextView = findViewById(R.id.vendor_forgetpassword)
        val vendor_signinemail : EditText = findViewById(R.id.vendor_signinemail)
        val vendor_signinpassword : EditText = findViewById(R.id.vendor_signinpassword)
        val vendor_signup : TextView = findViewById(R.id.vendor_signup)
        val switch_signin_vendor : Switch = findViewById(R.id.switch_signIn_vendor)

        switch_signin_vendor.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                intent = Intent(this, FSTSignIn::class.java)
                startActivity(intent)
                switch_signin_vendor.text = "Vendor"


            }

        }




        vendor_signinpassword.setOnClickListener{
            vendor_signinpassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }




        vendor_login.setOnClickListener {

//
//            if (vendor_signinemail.text.toString() == "admin" && vendor_signinpassword.text.toString() == "123") {
//                intent = Intent(this, AdminPanel :: class.java)
//                startActivity(intent)
//
//            } else {
//
//
//                intent = Intent(this, Vendor_Main_Page::class.java)
//                startActivity(intent)


                var pd = ProgressDialog(this)
                pd.setTitle("SignIp in progress...")
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                pd.setProgress(0)
                pd.setMax(100)


//
//    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

                if (vendor_signinemail.text.isNullOrEmpty()) {
                    vendor_signinemail?.error = "Email cannot be empty."
                    vendor_signinemail.isFocusable
                } else if (vendor_signinpassword?.text.isNullOrEmpty()) {
                    vendor_signinpassword?.error = "Password cannot be empty."
                    vendor_signinpassword.isFocusable

                }

//
//    else if (signinpassword?.text.length < 4) {
//        signinpassword?.error ="Password must contain more than 3 words."
//        signinpassword.isFocusable
//
//    }


                else if (vendor_signinemail.text.isEmpty() && vendor_signinpassword.text.isEmpty()) {
                    vendor_signinpassword?.error = "Empty."
                    vendor_signinemail?.error = "Empty."
                    vendor_signinemail.isFocusable
                } else if (vendor_signinemail.text.isNotEmpty() && vendor_signinpassword.text.isNotEmpty()) {

                    pd.show()

                    auth.signInWithEmailAndPassword(
                        vendor_signinemail.text.toString(),
                        vendor_signinpassword.text.toString()
                    )
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {

                                val user = auth.currentUser
                                updateUI(user, vendor_signinemail.text.toString())

                            } else

                                Toasty.warning(
                                    this,
                                    "Invalid Email or Password",
                                    Toast.LENGTH_SHORT
                                ).show()
                        }
                    pd.dismiss()


                }



        }


        vendor_forgetpassword.setOnClickListener(View.OnClickListener {

            intent = Intent(this, FSTForgetpassword::class.java)
            startActivity(intent)
        })

        vendor_signup.setOnClickListener(View.OnClickListener {

            intent = Intent(this, FST_Vendor_Signup::class.java)
            startActivity(intent)
        })


    }

    private fun updateUI(currentUser: FirebaseUser?, emailAdd: String) {
        if (currentUser != null) {

            if (currentUser.isEmailVerified) {

                val intent = Intent(this, Vendor_Main_Page::class.java)
                intent.putExtra("emailAddress", emailAdd);
                startActivity(intent)
                finish()
            }
            else
            {
                Toasty.info(this, "Kindly Verify your Email", Toast.LENGTH_SHORT).show()
            }

        }



    }


}


