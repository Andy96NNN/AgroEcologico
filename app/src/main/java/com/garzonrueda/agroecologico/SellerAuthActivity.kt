package com.garzonrueda.agroecologico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.garzonrueda.agroecologico.databinding.ActivityAdminAuthBinding
import com.garzonrueda.agroecologico.databinding.ActivitySellerAuthBinding
import com.google.firebase.auth.FirebaseAuth

class SellerAuthActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySellerAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivitySellerAuthBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        setup()
    }

    private fun setup() {

        title = "AgroEcologico - Iniciar Sesión - Vendedor"
        viewBinding.etSellerLogin.requestFocus()

        val usrName = viewBinding.etSellerLogin.text
        val pswd = viewBinding.etSellerPassword.text

        viewBinding.btnSellerLogin.setOnClickListener {
            if (usrName.isNotEmpty() && pswd.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        usrName.toString(), pswd.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            val email = it.result?.user?.email?: ""

                            goSellerHome(email, ProviderType.BASIC)
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

    private fun goSellerHome(email: String, provider: ProviderType) {

        viewBinding.etSellerLogin.text.clear()
        viewBinding.etSellerPassword.text.clear()

        val sellerHomeIntent = Intent(this, SellerHomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.toString())
        }
        startActivity(sellerHomeIntent)
        viewBinding.etSellerLogin.requestFocus()
    }
}