package com.garzonrueda.agroecologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.garzonrueda.agroecologico.databinding.ActivitySalesUnitBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SalesUnitActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySalesUnitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySalesUnitBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(viewBinding.root)

        val database = FirebaseDatabase.getInstance().reference

        setup(database)
    }

    private fun setup(db: DatabaseReference) {

        title = "AgroEcologico - Crear Unidad de Venta"

        viewBinding.btnUnitBack.setOnClickListener {
            onBackPressed()
        }

        viewBinding.btnUnitCreate.setOnClickListener {
            val input = viewBinding.etUnitName.text

            if (input.isNotEmpty()) {
                db.child("units").child(input.toString()).setValue(input.toString())
                Toast.makeText(this, "$input ingresado!", Toast.LENGTH_SHORT).show()
                viewBinding.etUnitName.text.clear()
            } else {
                Toast.makeText(this, "Ingrese el nombre de la unidad!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}