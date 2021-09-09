package com.garzonrueda.agroecologico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.garzonrueda.agroecologico.databinding.ActivityAdminAuthBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminAuthActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityAdminAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAdminAuthBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        // Eventos de Analytics
//        val analytics = FirebaseAnalytics.getInstance(this)
//        val bundle = Bundle()
//        bundle.putString("message", "Intergación de Firebase Completa!")
//        analytics.logEvent("InitScreen", bundle)

        // SETUP
        setup()

    }

    private fun setup() {

        title = "AgroEcologico - Iniciar Sesión - ADMIN"
        viewBinding.etUsrname.requestFocus()

        val usrName = viewBinding.etUsrname.text
        val pswd = viewBinding.pwdInput.text
//        val userDBReference = FirebaseDatabase.getInstance().reference
//
//        val listenerDB = object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                val data = snapshot.child(usrName).child("rol").toString()
//                Toast.makeText(this@AdminAuthActivity, data, Toast.LENGTH_LONG).show()
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        }

        viewBinding.loginButton.setOnClickListener {
            if (usrName.isNotEmpty() && pswd.isNotEmpty()) {


//                val query = userDBReference.child(usrName)
//                query.addValueEventListener(listenerDB)

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        usrName.toString(), pswd.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            val email = it.result?.user?.email?: ""

                            goHome(email, ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }

    }

    private fun showAlert() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha produicido un error en la autenticación")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun goHome(email: String, provider: ProviderType) {

        viewBinding.etUsrname.text.clear()
        viewBinding.pwdInput.text.clear()

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.toString())
        }
        startActivity(homeIntent)
        viewBinding.etUsrname.requestFocus()
    }
}