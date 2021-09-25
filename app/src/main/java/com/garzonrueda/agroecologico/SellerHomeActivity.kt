package com.garzonrueda.agroecologico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.garzonrueda.agroecologico.databinding.ActivitySellerHomeBinding

class SellerHomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySellerHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivitySellerHomeBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        setup()
    }

    private fun setup() {
        val bundle: Bundle? = intent.extras
        val sellerEmail = bundle?.getString("email")

        viewBinding.btnSalesPointView.setOnClickListener {
            val viewSPIntent = Intent(this, SalesPointViewActivity::class.java).apply {
                putExtra("email", sellerEmail)
            }
            startActivity(viewSPIntent)
        }
    }
}