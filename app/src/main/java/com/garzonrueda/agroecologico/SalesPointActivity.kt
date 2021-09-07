package com.garzonrueda.agroecologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.garzonrueda.agroecologico.databinding.ActivitySalesPointBinding
import com.google.firebase.database.FirebaseDatabase

class SalesPointActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySalesPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySalesPointBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        val database = FirebaseDatabase.getInstance().reference

        setup()

    }

    private fun setup() {

        title = "AgroEcologico - Crear Punto de Venta"

        viewBinding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}