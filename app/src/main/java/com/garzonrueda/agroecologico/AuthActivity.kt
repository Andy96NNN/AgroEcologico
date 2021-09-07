package com.garzonrueda.agroecologico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.garzonrueda.agroecologico.databinding.ActivityAuthBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AuthActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAuthBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        // Eventos de Analytics
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Intergación de Firebase Completa!")
        analytics.logEvent("InitScreen", bundle)

        // Database
        val database = FirebaseDatabase.getInstance().reference

        // SETUP
        setup(database)

    }

    private fun setup(database: DatabaseReference) {

        title = "Iniciar Sesión"

        viewBinding.loginButton.setOnClickListener {
            if (viewBinding.emailInput.text.isNotEmpty()
                && viewBinding.pwdInput.text.isNotEmpty()
            ) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        viewBinding.emailInput.text.toString(), viewBinding.pwdInput.text.toString()
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

        viewBinding.emailInput.text.clear()
        viewBinding.pwdInput.text.clear()

        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.toString())
        }
        startActivity(homeIntent)
        viewBinding.emailInput.requestFocus()
    }
}