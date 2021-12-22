package com.example.fst_cloud_new.SIGN_UP

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import android.widget.*
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SIGN_IN.FSTSignIn
import com.example.fst_cloud_new.Start_Pages.OTP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty

class FSTSignUp : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var signup : Button
    private lateinit var signupemail : EditText
    private lateinit var signupusername : EditText
    private lateinit var signupphoneno : EditText
    private lateinit var signuppassword : EditText
    private lateinit var signuprepassword : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fstsign_up)


        auth = Firebase.auth

        supportActionBar?.hide()

        signup  = findViewById(R.id.signup)
        val tvsignin : TextView = findViewById(R.id.user_signin)
        val authh : TextView = findViewById(R.id.tv2)
        signupemail  = findViewById(R.id.signupemail)
        signupusername  = findViewById(R.id.signupusername)
        signupphoneno  = findViewById(R.id.signupphoneno)
        signuppassword  = findViewById(R.id.signuppassword)
        signuprepassword  = findViewById(R.id.signuprepassword)
   //     val s : Switch = findViewById(R.id.switch1)




        val currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(this, HOMEPAGE::class.java))
            finish()
        }

        signup()


//
//        s.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                intent = Intent(this, FST_Vendor_Signup::class.java)
//                startActivity(intent)
//                s.text = "User"
//
//            }
//
//        }




        authh.setOnClickListener {
            intent = Intent(this, OTP::class.java)
            startActivity(intent)
        }

        signuppassword.setOnClickListener {
            signuppassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }


        signuprepassword.setOnClickListener {
            signuprepassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }









            tvsignin.setOnClickListener(View.OnClickListener {

                intent = Intent(this, FSTSignIn::class.java)
                startActivity(intent)
            })



    }

    private fun signup()
    {

        signup.setOnClickListener {

            var pd = ProgressDialog(this)
            pd.setTitle("SignUp in progress...")
            pd.setMessage("Please wait your data is fetching")
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            pd.setProgress(0)
            pd.setMax(100)


//            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)


            if (signupemail.text.isNullOrEmpty()) {
                signupemail?.error = "Email cannot be empty."
                signupemail.isFocusable
            } else if (signuppassword?.text.isNullOrEmpty()) {
                signuppassword?.error = "Password cannot be empty."
                signuppassword.isFocusable

            } else if (signuppassword?.text.length < 2) {
                signuppassword?.error = "Password must contain more than 3 words."
                signuppassword.isFocusable

            } else if (signupusername?.text.isNullOrEmpty()) {
                signupusername?.error = "Username Cannot be empty"
                signupusername.isFocusable

            } else if (signuprepassword?.text.isNullOrEmpty()) {
                signuprepassword?.error = "Confirm Password cannot be empty."
                signuprepassword.isFocusable

            } else if (signuppassword.text.toString() != signuprepassword.text.toString()) {
                signuprepassword?.error = "Password doesnot match."
                signuppassword.isFocusable


            } else if (signupemail.text.isEmpty() && signuppassword.text.isEmpty() &&
                signuprepassword.text.isEmpty() && signupusername.text.isEmpty() &&
                signupphoneno.text.isEmpty()
            ) {
                signupusername?.error = "Empty"
                signupemail?.error = "Empty"
                signuppassword?.error = "Empty"
                signuprepassword?.error = "Empty"
                signupusername.isFocusable
            } else {
                pd.show()

                auth.createUserWithEmailAndPassword(
                    signupemail.text.toString(),
                    signuppassword.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

// Below three lines added for sending the authentication link to the user's email
                            auth.currentUser?.sendEmailVerification()
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {


                                        val user = auth.currentUser

// below message changed and user is navigated to Sign In activity
//                                        textViewResponse.text =
//                                            "Sign Up successful. Verification link sent to the Email address"


                                        //Firestore Databse


                                        val db = Firebase.firestore
                                        //val db = FirebaseFirestore.getInstance()
                                        val users: MutableMap<String, Any> = HashMap()

                                        users["UserName"] = signupusername.text.toString()
                                        users["Email"] = signupemail.text.toString()
                                        users["PhoneNo"] = signupphoneno.text.toString()
                                        users["Password"] = signuppassword.text.toString()
                                        users["ConfirmPassword"] = signuprepassword.text.toString()

                                        db.collection("Users")
                                            .add(users)
                                            .addOnSuccessListener {
                                                Toasty.success(
                                                    this,
                                                    "Successfully added in DB",
                                                    Toast.LENGTH_SHORT
                                                ).show()

                                                pd.dismiss()

                                            }.addOnFailureListener {
                                                Toasty.error(
                                                    this,
                                                    "FAILED unable to add data to database",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()

                                            }




                                        intent = Intent(this, FSTSignIn::class.java)
                                        startActivity(intent)
                                    }
                                }
                        }
                    }.addOnFailureListener {
                        Toasty.info(
                            this,
                            "Sign Up failed, something went wrong OR account may already exist",
                            Toast.LENGTH_SHORT
                        ).show()

                        pd.dismiss()

                    }

            }
        }
    }
}

