package com.garzonrueda.agroecologico

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.garzonrueda.agroecologico.databinding.ActivityViewSpactivityBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class SalesPointViewActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityViewSpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewSpactivityBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        setup()
    }

    private fun setup() {

        Glide
            .with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/hu-agroecologico.appspot.com/o/appResources%2Fuser.png?alt=media&token=3cf1833c-5598-4432-ada4-5c73a0f89916")
            .into(viewBinding.imgSeller)

        viewBinding.btnCustomPoint.setOnClickListener {
            val bundle = intent.extras
            val intent = Intent(this, CustomSalesPointActivity::class.java).apply {
                putExtra("email", bundle?.getString("email"))
            }
            startActivity(intent)
        }
    }
}