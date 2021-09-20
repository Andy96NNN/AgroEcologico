package com.garzonrueda.agroecologico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.garzonrueda.agroecologico.databinding.ActivitySalesPointBinding
import com.google.firebase.auth.FirebaseAuth
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

        val db = FirebaseDatabase.getInstance().reference

        viewBinding.backButton.setOnClickListener {
            onBackPressed()
        }

        viewBinding.btnCreate.setOnClickListener {
            if (
                viewBinding.etPointName.text.isNotEmpty()
                && viewBinding.etSellerId.text.isNotEmpty()
                && viewBinding.etSellerPwd.text.isNotEmpty()
                && viewBinding.etPhone.text.isNotEmpty()
                && viewBinding.etSellerEmail.text.isNotEmpty()
            ) {
                db.child("Users").child(viewBinding.etSellerId.text.toString()).child("name").setValue(viewBinding.etPointName.text.toString())
                db.child("Users").child(viewBinding.etSellerId.text.toString()).child("password").setValue(viewBinding.etSellerPwd.text.toString())
                db.child("Users").child(viewBinding.etSellerId.text.toString()).child("phone").setValue(viewBinding.etPhone.text.toString())
                db.child("Users").child(viewBinding.etSellerId.text.toString()).child("email").setValue(viewBinding.etSellerEmail.text.toString())
                db.child("Users").child(viewBinding.etSellerId.text.toString()).child("rol").setValue("seller")

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    viewBinding.etSellerEmail.text.toString(),
                    viewBinding.etSellerPwd.text.toString()
                ).addOnCompleteListener {

                    Toast.makeText(this, "Punto de venta regsitrado", Toast.LENGTH_LONG).show()
                    clearFields()

                }.addOnFailureListener {

                    Toast.makeText(this, "Registro Fallido", Toast.LENGTH_LONG).show()
                    viewBinding.etPointName.requestFocus()
                }


            } else {
                Toast.makeText(this, "Rellene todos los campos!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun clearFields() {
        viewBinding.etPhone.text.clear()
        viewBinding.etPointName.text.clear()
        viewBinding.etSellerEmail.text.clear()
        viewBinding.etSellerId.text.clear()
        viewBinding.etSellerPwd.text.clear()
    }
}