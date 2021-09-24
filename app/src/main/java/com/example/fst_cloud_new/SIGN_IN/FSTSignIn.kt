package com.example.fst_cloud_new.SIGN_IN

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fst_cloud_new.HOMEPAGE.HOMEPAGE
import com.example.fst_cloud_new.R
import com.example.fst_cloud_new.SIGN_UP.FSTSignUp
import com.example.fst_cloud_new.Start_Pages.FSTForgetpassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty

class FSTSignIn : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fstsign_in)

        supportActionBar?.hide()

        auth = Firebase.auth

        val login : Button = findViewById(R.id.login)
        val tvsignup : TextView = findViewById(R.id.user_signup)
        val forgetpassword : TextView = findViewById(R.id.forgetpassword)
        val signinemail : EditText = findViewById(R.id.signinemail)
        val signinpassword : EditText = findViewById(R.id.signinpassword)
        val switch_signin_user : Switch = findViewById(R.id.switch_signIn_user)

        switch_signin_user.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                intent = Intent(this, FST_Vendor_Signin::class.java)
                startActivity(intent)
                switch_signin_user.text = "User"


            }

        }




        signinpassword.setOnClickListener{
            signinpassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }




        login.setOnClickListener(View.OnClickListener {

            var pd = ProgressDialog(this)
            pd.setTitle("SignIp in progress...")
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            pd.setProgress(0)
            pd.setMax(100)


//
//    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

            if (signinemail.text.isNullOrEmpty()) {
                signinemail?.error ="Email cannot be empty."
                signinemail.isFocusable
            }

            else if (signinpassword?.text.isNullOrEmpty()) {
                signinpassword?.error ="Password cannot be empty."
                signinpassword.isFocusable

            }

//
//    else if (signinpassword?.text.length < 4) {
//        signinpassword?.error ="Password must contain more than 3 words."
//        signinpassword.isFocusable
//
//    }


            else if (signinemail.text.isEmpty() && signinpassword.text.isEmpty()) {
                signinpassword?.error ="Empty."
                signinemail?.error ="Empty."
                signinemail.isFocusable
            }

            else if (signinemail.text.isNotEmpty() && signinpassword.text.isNotEmpty()) {

                pd.show()

                auth.signInWithEmailAndPassword(
                    signinemail.text.toString(),
                    signinpassword.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val user = auth.currentUser
                            updateUI(user,signinemail.text.toString())

                        }
                        else

                            Toasty.error(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                    }
                pd.dismiss()


            }



        })



        tvsignup.setOnClickListener(View.OnClickListener {

            intent = Intent(this, FSTSignUp::class.java)
            startActivity(intent)
        })

        forgetpassword.setOnClickListener(View.OnClickListener {

            intent = Intent(this, FSTForgetpassword::class.java)
            startActivity(intent)
        })


    }

    private fun updateUI(currentUser: FirebaseUser?, emailAdd: String) {
        if (currentUser != null) {

            if (currentUser.isEmailVerified) {

                val intent = Intent(this, HOMEPAGE::class.java)
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