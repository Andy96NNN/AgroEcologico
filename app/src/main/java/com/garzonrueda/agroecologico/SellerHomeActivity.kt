package com.garzonrueda.agroecologico

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

//        setup()
    }

    private fun setup() {
        TODO("Not yet implemented")
    }
}