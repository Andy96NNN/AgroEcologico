package com.garzonrueda.agroecologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.garzonrueda.agroecologico.databinding.ActivityCustomSalesPointBinding

class CustomSalesPointActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityCustomSalesPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityCustomSalesPointBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)


        setup()
    }

    private fun setup() {

        Glide
            .with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/hu-agroecologico.appspot.com/o/appResources%2Fadd.png?alt=media&token=2a9c8562-a6e4-4de0-8c9b-9548a022dc18")
            .into(viewBinding.imgSeller2)

        viewBinding.imgSeller2.setOnClickListener {
            // TODO
        }
    }
}