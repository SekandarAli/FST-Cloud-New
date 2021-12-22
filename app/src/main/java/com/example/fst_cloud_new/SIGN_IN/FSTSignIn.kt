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
import com.example.fst_cloud_new.databinding.ActivityFstsignInBinding
import com.example.fst_cloud_new.databinding.ActivityHomepageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import java.lang.Exception


class FSTSignIn : AppCompatActivity() {

    lateinit var firebaseAuthuth : FirebaseAuth
    lateinit var binding : ActivityFstsignInBinding

    private lateinit var googlesignTnclient : GoogleSignInClient



    private companion object {
        private val RC_SIGN_IN = 108
        private const val TAG = "G00GLE_SIGNIN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fstsign_in)

        supportActionBar?.hide()

        firebaseAuthuth = Firebase.auth

        val login : Button = findViewById(R.id.login)
       // val googleSignInButton : Button = findViewById(R.id.google_signInBtn)
        val tvsignup : TextView = findViewById(R.id.user_signup)
        val forgetpassword : TextView = findViewById(R.id.forgetpassword)
        val signinemail : EditText = findViewById(R.id.signinemail)
        val signinpassword : EditText = findViewById(R.id.signinpassword)
       // val switch_signin_user : Switch = findViewById(R.id.switch_signIn_user)







        val googlesignInOptions =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

            googlesignTnclient = GoogleSignIn.getClient(this, googlesignInOptions)


        firebaseAuthuth = FirebaseAuth.getInstance()
        checkUser()



//        googleSignInButton.setOnClickListener(View.OnClickListener {
//
//
//            val intent = googlesignTnclient.signInIntent
//            startActivityForResult(intent, RC_SIGN_IN)
//
//        })






//
//        switch_signin_user.setOnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                intent = Intent(this, FST_Vendor_Signin::class.java)
//                startActivity(intent)
//                switch_signin_user.text = "User"
//
//
//            }
//
//        }




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

                firebaseAuthuth.signInWithEmailAndPassword(
                    signinemail.text.toString(),
                    signinpassword.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val user = firebaseAuthuth.currentUser
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

    private fun checkUser() {

        val firebaseUser = firebaseAuthuth.currentUser
        if(firebaseUser != null)
        {
            startActivity(Intent(this,HOMEPAGE::class.java))
            finish()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }

            catch (e: Exception)
            {

            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {



        val credential = GoogleAuthProvider.getCredential(account!!.idToken,null)
        firebaseAuthuth.signInWithCredential(credential)
            .addOnSuccessListener{ authResult->

                val firebaseUser = firebaseAuthuth.currentUser
                val uid = firebaseUser!!.uid
                val email = firebaseUser!!.email

                if(authResult.additionalUserInfo!!.isNewUser)
                {
                    Toast.makeText(this, "ACC CREAtED ", Toast.LENGTH_SHORT).show()
                }

                else
                {
                    Toast.makeText(this, "login", Toast.LENGTH_SHORT).show()
                }


                startActivity(Intent(this,HOMEPAGE::class.java))
                finish()
            }

            .addOnFailureListener { e->

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }
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