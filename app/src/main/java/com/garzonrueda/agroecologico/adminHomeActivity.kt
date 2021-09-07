package com.garzonrueda.agroecologico

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.garzonrueda.agroecologico.databinding.ActivityAdminhomeBinding
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}



class HomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityAdminhomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAdminhomeBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        // SETUP

        val bundle: Bundle? = intent.extras
        val email: String? = bundle?.getString("email")
        val provider: String? = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")

    }

    private fun setup(email: String, provider: String) {

        title = "Inicio - AgroEcologico"
        viewBinding.tvEmail.text = email
        viewBinding.tvProvider.text = provider

        viewBinding.createSalePointButton.setOnClickListener {
            goCreateSalesPoint()
        }

        viewBinding.addSaleUnitButton.setOnClickListener {
            goAddSalesUnit()
        }

        viewBinding.logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    private fun goAddSalesUnit() {

        val addSUIntent = Intent(this, SalesUnitActivity::class.java).apply {

        }
        startActivity(addSUIntent)

    }

    private fun goCreateSalesPoint() {

        val createSPIntent = Intent(this, SalesPointActivity::class.java).apply {

        }
        startActivity(createSPIntent)

    }
}