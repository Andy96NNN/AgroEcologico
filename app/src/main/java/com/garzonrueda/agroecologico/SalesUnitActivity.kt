package com.garzonrueda.agroecologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.garzonrueda.agroecologico.databinding.ActivitySalesUnitBinding
import com.google.firebase.database.FirebaseDatabase

class SalesUnitActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySalesUnitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySalesUnitBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        val database = FirebaseDatabase.getInstance().reference

        setup()
    }

    private fun setup() {

        title = "AgroEcologico - Crear Unidad de Venta"

        viewBinding.btnUnitBack.setOnClickListener {
            onBackPressed()
        }

    }
}