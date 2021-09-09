package com.garzonrueda.agroecologico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.garzonrueda.agroecologico.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        setup()
    }

    private fun setup() {

        viewBinding.button4.setOnClickListener {
            goAdminLogin()
        }

        viewBinding.button5.setOnClickListener {
            goSellerLogin()
        }

    }

    private fun goSellerLogin() {
        val sellerLoginIntent = Intent(this, SellerAuthActivity::class.java).apply {

        }
        startActivity(sellerLoginIntent)
    }

    private fun goAdminLogin() {
        val adminLoginIntent = Intent(this, AdminAuthActivity::class.java).apply {

        }
        startActivity(adminLoginIntent)
    }
}